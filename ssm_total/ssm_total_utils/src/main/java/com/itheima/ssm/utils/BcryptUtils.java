package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密工具类
 */
public class BcryptUtils {
    public static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encoder(String password){
        String encode = bCryptPasswordEncoder.encode(password);
        return encode;
    }
}
