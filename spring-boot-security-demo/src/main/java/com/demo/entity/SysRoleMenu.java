package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.demo.entity.SysRoleMenu")
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu {
    /**
     * ID
     */
     @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    /**
     * 权限ID
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value="权限ID")
    private Long menuId;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_MENU_ID = "menu_id";
}