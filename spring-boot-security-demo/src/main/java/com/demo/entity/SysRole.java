package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.demo.entity.SysRole")
@Data
@TableName(value = "sys_role")
public class SysRole {
    /**
     * 角色ID
     */
     @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value="角色名称")
    private String roleName;

    public static final String COL_ROLE_NAME = "role_name";
}