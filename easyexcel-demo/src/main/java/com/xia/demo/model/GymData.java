package com.xia.demo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class GymData extends BaseRowModel {
    @ExcelProperty(value = "名称", index = 0)
    private String name;

    @ExcelProperty(value = "经度", index = 1)
    private String longitude;

    @ExcelProperty(value = "纬度", index = 2)
    private String latitude;

    @ExcelProperty(value = "地址", index = 3)
    private String address;

    @ExcelProperty(value = "电话", index = 4)
    private String phone;

    @ExcelProperty(value = "类别", index = 5)
    private String classs;
}