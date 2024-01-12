package com.lzy.headline.test;

import com.lzy.headline.util.JwtUtil;
import org.junit.Test;

public class TestJwtUtil {
    @Test
    public void testAllMethod(){
        String token = JwtUtil.createToken(1L);
        System.out.println(token);
        Long userId = JwtUtil.getUserId(token);
        System.out.println(userId);
        System.out.println(JwtUtil.isExpiration(token));


    }
}
