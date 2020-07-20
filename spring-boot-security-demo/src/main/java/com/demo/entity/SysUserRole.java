package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.demo.entity.SysUserRoleService")
@Data
@TableName(value = "sys_user_role")
public class SysUserRole {
    /**
     * ID
     */
     @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ROLE_ID = "role_id";
}