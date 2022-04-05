package com.shf.log;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义日志拦截器
 */
public class MyLogInterceptor extends HandlerInterceptorAdapter  {
    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    /**
     * 在controller方法执行前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        获得被拦截的方法对象
        Method method = handlerMethod.getMethod();
//        获取方法上的注解
        MyLog annotation = method.getAnnotation(MyLog.class);
        if (annotation != null){
//            说明当前拦截到的方法上加入了MyLog注解
            long startTime = System.currentTimeMillis();
            startTimeThreadLocal.set(startTime);
        }
        return true;
    }

    /**
     * 在controller方法执行后执行方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        获得被拦截的方法对象
        Method method = handlerMethod.getMethod();
//        获取方法上的注解
        MyLog annotation = method.getAnnotation(MyLog.class);

        if (annotation!=null){
            Long startTime = startTimeThreadLocal.get();
            Long endTime = System.currentTimeMillis();
//            计算Controller方法的执行事件
            Long optTime = endTime - startTime;

            String requestUri = request.getRequestURI();
            String methodName = method.getDeclaringClass().getName() + "." + method.getName();
            String methodDesc = annotation.desc();

            System.out.println("请求URI："+requestUri);
            System.out.println("请求方法名："+methodName);
            System.out.println("方法描述："+methodDesc);
            System.out.println("方法执行时间："+optTime+"ms");
        }
    }
}
