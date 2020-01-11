package com.xia.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xiafb
 * @date Created in 2020/1/9 11:30
 * description 博客实体
 * modified By
 * version  1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class Blog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8776980890463845694L;

    private String title;

    private String content;
}
