package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.SysUserMapper;
import com.demo.entity.SysMenu;
import com.demo.entity.SysRole;
import com.demo.entity.SysUser;
import com.demo.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser selectUserByName(String userName) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUser::getUsername, userName);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        return baseMapper.selectSysRoleByUserId(userId);
    }

    @Override
    public List<SysMenu> selectSysMenuByUserId(Long userId) {
        return baseMapper.selectSysMenuByUserId(userId);
    }
}
