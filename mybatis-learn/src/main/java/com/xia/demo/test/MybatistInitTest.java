package com.xia.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.xia.demo.dao.BlogMapper;
import com.xia.demo.entity.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiafb
 * @date Created in 2020/1/9 14:34
 * description
 * modified By
 * version
 */
public class MybatistInitTest {

    public static void main(String[] args) {
        loadByXml();
    }

    private static void loadByXml(){
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = null;
        SqlSession sqlSession = null;
        try {
            resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
            sqlSession = build.openSession();
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog zs = mapper.selectByTitle("张三");
            System.out.println(JSON.toJSONString(zs));
        } catch (IOException e) {
            System.err.println("获取配置文件失败");
        } catch (Exception ex){
            System.err.println("创建SqlSessionFactory失败");
            ex.printStackTrace();
        }finally {
            IOUtils.close(resourceAsStream);
            if(sqlSession != null){
                sqlSession.close();
            }

        }
    }
}
