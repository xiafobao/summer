package com.xia.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiafb
 * @date Created in 2020/1/9 11:27
 * description 基础entity
 * modified By
 * version
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1059196088490535813L;

    private String id;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;
}
