package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.SysMenu;
import com.demo.entity.SysRole;
import com.demo.entity.SysUser;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户id查询菜单信息
     * @param userId 用户id
     * @return List<SysMenu>
     */
    List<SysMenu> selectSysMenuByUserId(Long userId);

    /**
     * 根据用户id查询角色信息
     * @param userId 用户id
     * @return List<SysRole>
     */
    List<SysRole> selectSysRoleByUserId(Long userId);
}