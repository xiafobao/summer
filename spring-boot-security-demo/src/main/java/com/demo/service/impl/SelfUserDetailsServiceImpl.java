package com.demo.service.impl;

import com.demo.entity.SelfUserEntity;
import com.demo.entity.SysUser;
import com.demo.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author xiafb
 * @date Created in 2020/5/19 10:44
 * description 用户的业务实现
 * modified By
 * version  1.0
 */
@Component
public class SelfUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public SelfUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysUser sysUserEntity =sysUserService.selectUserByName(username);
        if (sysUserEntity != null){
            // 组装参数
            SelfUserEntity selfUserEntity = new SelfUserEntity();
            BeanUtils.copyProperties(sysUserEntity,selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}
