package com.demo.handler;

import cn.hutool.core.util.StrUtil;
import com.demo.entity.SelfUserEntity;
import com.demo.entity.SysRole;
import com.demo.enums.UserStatusEnum;
import com.demo.service.SysUserService;
import com.demo.service.impl.SelfUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiafb
 * @date Created in 2020/5/19 10:39
 * description 登录验证类（身份验证提供者）
 * modified By
 * version  1.0
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SelfUserDetailsServiceImpl selfUserDetailsService;
    @Autowired
    private SysUserService sysUserService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取表单输入的用户名
        String name = (String) authentication.getPrincipal();
        //获取表单输入的密码
        String pwd = (String) authentication.getCredentials();
        //根据用户名查询用户是否存在
        SelfUserEntity userDetails = selfUserDetailsService.loadUserByUsername(name);
        //判断是否存在该用户名用户
        if(userDetails == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //判断密码是否正确
        if(!new BCryptPasswordEncoder().matches(pwd, userDetails.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
        //判断用户状态是否正常
        if(StrUtil.equals(userDetails.getStatus(), UserStatusEnum.PROHIBIT.getCode())){
            throw new LockedException("该用户已被冻结");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SysRole> sysRoleEntityList = sysUserService.selectSysRoleByUserId(userDetails.getUserId());
        for (SysRole sysRoleEntity: sysRoleEntityList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
        }
        userDetails.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userDetails, pwd, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
