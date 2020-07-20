package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.demo.entity.SysUser")
@Data
@TableName(value = "sys_user")
public class SysUser {
    /**
     * 用户ID
     */
     @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 状态 PROHIBIT：禁用   NORMAL：正常
     */
    @TableField(value = "status")
    @ApiModelProperty(value="状态 PROHIBIT：禁用   NORMAL：正常")
    private String status;

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_STATUS = "status";
}