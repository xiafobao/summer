package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.demo.entity.SysMenu")
@Data
@TableName(value = "sys_menu")
public class SysMenu {
    /**
     * ID
     */
     @TableId(value = "menu_id", type = IdType.AUTO)
    @ApiModelProperty(value="ID")
    private Long menuId;

    /**
     * 权限名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="权限名称")
    private String name;

    /**
     * 权限标识
     */
    @TableField(value = "permission")
    @ApiModelProperty(value="权限标识")
    private String permission;

    public static final String COL_NAME = "name";

    public static final String COL_PERMISSION = "permission";
}