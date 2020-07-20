package com.demo.handler;

import com.demo.entity.SelfUserEntity;
import com.demo.entity.SysMenu;
import com.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiafb
 * @date Created in 2020/5/19 11:25
 * description 自定义权限注解验证
 * modified By
 * version  1.0
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private SysUserService sysUserService;
    /**
     * hasPermission鉴权方法
     * 这里仅仅判断PreAuthorize注解中的权限表达式
     * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
     * Param  authentication  用户身份
     * Param  targetUrl  请求路径
     * Param  permission 请求路径权限
     * Return boolean 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获取用户信息
        SelfUserEntity selfUserEntity =(SelfUserEntity) authentication.getPrincipal();
        // 查询用户权限(这里可以将权限放入缓存中提升效率)
        Set<String> permissions = new HashSet<>();
        List<SysMenu> sysMenuEntityList = sysUserService.selectSysMenuByUserId(selfUserEntity.getUserId());
        for (SysMenu sysMenuEntity:sysMenuEntityList) {
            permissions.add(sysMenuEntity.getPermission());
        }
        // 权限对比
        return permissions.contains(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
