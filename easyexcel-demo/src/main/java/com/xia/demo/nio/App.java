package com.xia.demo.nio;

import com.xia.demo.nio.model.GymData;
import com.xia.demo.nio.util.ExcelUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("深圳.xlsx");
        List<GymData> baseRowModels = ExcelUtil.readExcel(systemResourceAsStream, GymData.class);

    }
}
