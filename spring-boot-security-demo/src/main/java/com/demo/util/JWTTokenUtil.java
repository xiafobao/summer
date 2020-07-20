package com.demo.util;

import cn.hutool.json.JSONUtil;
import com.demo.config.JWTConfig;
import com.demo.entity.SelfUserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xiafb
 * @date Created in 2020/5/6 15:30
 * description JWT工具类
 * modified By
 * version  1.0
 */
@Slf4j
@Component
public class JWTTokenUtil {

    private JWTTokenUtil(){}

    /**
     * 根据用户信息生成token
     * @param userEntity 用户信息
     * @return token
     */
    public static String createAccessToken(SelfUserEntity userEntity){
        return Jwts.builder()
                //放入用户名和id
                .setId(userEntity.getUserId() + "")
                //主题
                .setSubject(userEntity.getUsername())
                //签发时间
                .setIssuedAt(new Date())
                //签发者
                .setIssuer("system")
                //自定义属性
                .claim("authorities", JSONUtil.toJsonStr(userEntity.getAuthorities()))
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
                .compact();
    }
}
