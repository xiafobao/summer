package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.SysMenu;
import com.demo.entity.SysRole;
import com.demo.entity.SysUser;

import java.util.List;

/**
 * @author xiafb
 * @date Created in 2020/5/6 17:51
 * description
 * modified By
 * version
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名称查询用户信息
     * @param userName 用户名称
     * @return SysUser
     */
    SysUser selectUserByName(String userName);

    /**
     * 根据用户id查询角色信息
     * @param userId 用户id
     * @return List<SysRole>
     */
    List<SysRole> selectSysRoleByUserId(Long userId);

    /**
     * 根据用户id查询菜单信息
     * @param userId 用户id
     * @return List<SysRole>
     */
    List<SysMenu> selectSysMenuByUserId(Long userId);
}
