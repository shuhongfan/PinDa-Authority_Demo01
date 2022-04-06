package com.shf.pinda.zuul.filter;

import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.context.RequestContext;
import com.shf.pinda.authority.dto.auth.ResourceQueryDTO;
import com.shf.pinda.authority.entity.auth.Resource;
import com.shf.pinda.base.R;
import com.shf.pinda.common.constant.CacheKey;
import com.shf.pinda.context.BaseContextConstants;
import com.shf.pinda.exception.code.ExceptionCode;
import com.shf.pinda.zuul.api.ResourceApi;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限验证过滤器
 */
@Component
@Slf4j
public class AccessFilter extends BaseFilter {
    @Autowired
    private CacheChannel cacheChannel;

    @Autowired
    private ResourceApi resourceApi;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 验证当前用户是否拥有某个URI的访问权限
     */
    @Override
    public Object run() {
//        第1步：判断当前请求uri是否需要忽略
        // 不进行拦截的地址
        if (isIgnoreToken()) {
            return null;
        }

//        第2步：获取当前请求的请求方式和uri，拼接成GET/user/page这种形式，称为权限标识符
        RequestContext requestContext = RequestContext.getCurrentContext();
        String requestURI = requestContext.getRequest().getRequestURI();
        requestURI = StrUtil.subSuf(requestURI, zuulPrefix.length());
        requestURI = StrUtil.subSuf(requestURI, requestURI.indexOf("/", 1));
        String method = requestContext.getRequest().getMethod();
        String permission = method + requestURI;

//        第3步：从缓存中获取所有需要进行鉴权的资源(同样是由资源表的method字段值+url字段值拼接成)，如果没有获取到则通过Feign调用权限服务获取并放入缓存中
        //从缓存中获取所有需要进行鉴权的资源
        CacheObject resourceNeed2AuthObject =
                cacheChannel.get(CacheKey.RESOURCE,
                        CacheKey.RESOURCE_NEED_TO_CHECK);
        List<String> resourceNeed2Auth =
                (List<String>) resourceNeed2AuthObject.getValue();
        if(resourceNeed2Auth == null){
//            缓存中没有对应的数据
            resourceNeed2Auth = resourceApi.list().getData();
//            放入缓存中
            if(resourceNeed2Auth != null){
                cacheChannel.set(CacheKey.RESOURCE,
                        CacheKey.RESOURCE_NEED_TO_CHECK,
                        resourceNeed2Auth);
            }
        }
        if(resourceNeed2Auth != null){
            long count = resourceNeed2Auth.stream().filter((String r) -> {
                return permission.startsWith(r);
            }).count();
            if(count == 0){
                //未知请求
                errorResponse(ExceptionCode.UNAUTHORIZED.getMsg(),
                        ExceptionCode.UNAUTHORIZED.getCode(), 200);
                return null;
            }
        }
//        第5步：如果包含当前的权限标识符，则从zuul header中取出用户id，
//        根据用户id取出缓存中的用户拥有的权限，如果没有取到则通过Feign调用权限服务获取并放入缓存，
//        判断用户拥有的权限是否包含当前请求的权限标识符
        String userId = requestContext.getZuulRequestHeaders().
                get(BaseContextConstants.JWT_KEY_USER_ID);
        CacheObject cacheObject = cacheChannel.get(CacheKey.USER_RESOURCE, userId);
        List<String> userResource = (List<String>) cacheObject.getValue();
        // 如果从缓存获取不到当前用户的资源权限，需要查询数据库获取，然后再放入缓存
        if(userResource == null){
            ResourceQueryDTO resourceQueryDTO = new ResourceQueryDTO();
            resourceQueryDTO.setUserId(new Long(userId));
            //通过Feign调用服务，查询当前用户拥有的权限
            R<List<Resource>> result = resourceApi.visible(resourceQueryDTO);
            if(result.getData() != null){
                List<Resource> userResourceList = result.getData();
                userResource = userResourceList.stream().map((Resource r) -> {
                    return r.getMethod() + r.getUrl();
                }).collect(Collectors.toList());
                cacheChannel.set(CacheKey.USER_RESOURCE,userId,userResource);
            }
        }

//        第6步：如果用户拥有的权限包含当前请求的权限标识符则说明当前用户拥有权限，直接放行
        long count = userResource.stream().filter((String r) -> {
            return permission.startsWith(r);
        }).count();

//        第7步：如果用户拥有的权限不包含当前请求的权限标识符则说明当前用户没有权限，返回未经授权错误提示
        if(count > 0){
            //有访问权限
            return null;
        }else{
            log.warn("用户{}没有访问{}资源的权限",userId,method + requestURI);
            errorResponse(ExceptionCode.UNAUTHORIZED.getMsg(),
                    ExceptionCode.UNAUTHORIZED.getCode(), 200);
        }
        return null;
    }
}