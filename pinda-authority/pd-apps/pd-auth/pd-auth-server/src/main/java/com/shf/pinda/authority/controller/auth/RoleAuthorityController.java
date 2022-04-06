package com.shf.pinda.authority.controller.auth;
import java.util.List;
import com.shf.pinda.authority.biz.service.auth.RoleAuthorityService;
import com.shf.pinda.authority.entity.auth.RoleAuthority;
import com.shf.pinda.base.BaseController;
import com.shf.pinda.base.R;
import com.shf.pinda.database.mybatis.conditions.Wraps;
import com.shf.pinda.dozer.DozerUtils;
import com.shf.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 前端控制器
 * 角色的资源
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roleAuthority")
@Api(value = "RoleAuthority", tags = "角色的资源")
public class RoleAuthorityController extends BaseController {
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    /**
     * 查询指定角色关联的菜单和资源
     */
    @ApiOperation(value = "查询指定角色关联的菜单和资源", notes = "查询指定角色关联的菜单和资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/{roleId}")
    @SysLog("查询指定角色关联的菜单和资源")
    public R<List<RoleAuthority>> page(@PathVariable Long roleId) {
        return success(roleAuthorityService.list(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, roleId)));
    }
}
