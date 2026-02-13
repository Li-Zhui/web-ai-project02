package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 生成JWT令牌
     */
    @Test
    public void testGenerateJwt(){
        Map<String , Object> dataMap = new HashMap<>();
        dataMap.put("id" , 1);
        dataMap.put("username" , "admin");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256 , "itheima") //设置签名算法.指定密钥
                .setClaims(dataMap) //设置自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 )) //设置令牌过期时间
                .compact(); //生成令牌
        System.out.println(jwt);

    }


    /**
     *解析JWT令牌
     */
    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzcwOTU1MzA5LCJ1c2VybmFtZSI6ImFkbWluIn0.zDkwbWB-M8Tc4LFnbVBpkUI0qaeK0VTD3reFXrMwaQg";
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")    //生成和解析令牌时所使用的密钥必须完全一致
                .parseClaimsJws(token)    //解析令牌
                .getBody();     //获取自定义数据(Map集合)
        System.out.println(claims);
    }

}
