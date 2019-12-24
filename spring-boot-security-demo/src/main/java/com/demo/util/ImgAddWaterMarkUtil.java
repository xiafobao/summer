package com.demo.util;

import org.apache.logging.log4j.util.PropertiesUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiafb
 * @date Created in 2019/12/16 11:01
 * description
 * modified By
 * version
 */
public class ImgAddWaterMarkUtil {

    public static void addWaterMark(String srcImgPath, String tarImgPath, String date,
                                    String ms, String name, String address) throws IOException {
        Color color = new Color(255, 255, 255);
        Font font = new Font("Microsoft YaHei UI", Font.PLAIN, 30);
        // 读取原图片信息
        //得到文件
        File srcImgFile = new File(srcImgPath);
        //文件转化为图片
        Image srcImg = ImageIO.read(srcImgFile);
        //获取图片的宽
        int srcImgWidth = srcImg.getWidth(null);
        //获取图片的高
        int srcImgHeight = srcImg.getHeight(null);
        // 加水印
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        //根据图片的背景设置水印颜色
        g.setColor(color);
        //设置字体
        g.setFont(font);
        //设置水印的坐标
        //int x0 = getWatermarkLength(waterMarkContent.get(0), g);
        //int y0 = getWatermarkLength(waterMarkContent.get(0), g);
        //画出水印
        g.drawString("赋力", 15, 50);
        //设置水印的坐标
        //int x1 = getWatermarkLength(waterMarkContent.get(1), g);
        //int y1 = getWatermarkLength(waterMarkContent.get(1), g) + 20;
        //画出水印
        g.drawString(date, 15, 90);
        //设置水印的坐标
        //int x2 = getWatermarkLength(waterMarkContent.get(2), g);
        //int y2 = getWatermarkLength(waterMarkContent.get(2), g) + 20;
        //画出水印
        g.drawString(ms, 15, 130);
        //设置水印的坐标
        int x3 = srcImgWidth - getWatermarkLength(ms, g) + 20;
        int y3 = srcImgHeight - getWatermarkLength(ms, g) + 20;
        //画出水印
        g.drawString(name, x3-40, srcImgHeight - 60);
        //设置水印的坐标
        int x4 = srcImgWidth - getWatermarkLength(address, g);
        int y4 = srcImgHeight - getWatermarkLength(address, g);
        //画出水印
        g.drawString(address, x4-5, srcImgHeight - 20);

        // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
        ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
        InputStream me = classLoader.getResourceAsStream("me.png");
        InputStream location = classLoader.getResourceAsStream("location.png");
        BufferedImage imgIcon1 = ImageIO.read(me);
        BufferedImage imgIcon2 = ImageIO.read(location);
        // 得到Image对象。
        Image img1 = imgIcon1.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        Image img2 = imgIcon2.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        //float alpha = 0.5f; // 透明度

        // 表示水印图片的位置
        g.drawImage(img1, x3 - 80, srcImgHeight - 95, null);
        //g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OVER));
        g.drawImage(img2, x4 - 50, srcImgHeight - 50, null);

        //g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OVER));
        g.dispose();

        // 输出图片
        FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
        String srcFileName = srcImgFile.getName();
        String srcFileType = srcFileName.substring(srcFileName.lastIndexOf(".") + 1, srcFileName.length());
        ImageIO.write(bufImg, srcFileType, outImgStream);
        System.out.println("添加水印完成");
        outImgStream.flush();
        outImgStream.close();
    }


    private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void main(String[] args) throws IOException {

        SimpleDateFormat f= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat f2= new SimpleDateFormat("HH:mm");
        String format = f.format(new Date());
        String format2 = f2.format(new Date());
        List<String> waterMarkContent = new ArrayList<>();
        waterMarkContent.add("赋力");
        waterMarkContent.add(format);
        waterMarkContent.add(format2);
        waterMarkContent.add("张三峰");
        waterMarkContent.add("深圳市南山区侨城西街");
        List<String> icoPaths = new ArrayList<>();
        icoPaths.add("C:\\Users\\fuli\\Pictures\\member.png");
        icoPaths.add("C:\\Users\\fuli\\Pictures\\address.png");
        addWaterMark("C:\\Users\\fuli\\Pictures\\dd.jpg",
                "C:\\Users\\fuli\\Pictures\\cc.jpg", format, format2,
                "杨宇","广东省深圳市宝安区留仙一路2号");
    }
}
