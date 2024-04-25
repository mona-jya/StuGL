package com.mona.stugl.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mona.stugl.user.domain.User;

import java.util.Date;

/**
 * @ClassName MyTokenUtil
 * @Author mona
 * @Date 2024/4/25 14:53
 * @Version 1.0
 * @Description TODO
 **/
public class MyTokenUtil {
    //密钥盐
    private static final String TOKEN_KEY = "mona呀！";
    //令牌有效期（毫秒）
    private static final Long EXPIRE_TIME = 10*60*60*1000L;

    /**
     * 生成令牌
     */
    public static String createToken(User user){
        Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token = JWT.create()//创建JWT
                .withIssuer("mona")//发布者
                .withClaim("username", user.getName())//携带信息
                .withClaim("password", user.getPassword())
                .withExpiresAt(expiresAt)//过期时间
                .sign(Algorithm.HMAC256(TOKEN_KEY));//加密算法

        return token;
    }

    public static boolean verifyToken(String token){
        try {
            JWT.require(Algorithm.HMAC256(TOKEN_KEY))//传递密钥
                    .withIssuer("mona")//发布者
                    .build()//创建JWT
                    .verify(token);//验证令牌
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
