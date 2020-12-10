package com.conference.service;

import com.conference.entity.Driver;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @ClassName: TokenService
 * @Description: That's enough.
 * @Author: Lance
 * @Date: 2020/12/7 10:20
 */
public interface TokenService {
    public String getToken(Driver driver);

    //将增加用户的人数写入token
    public String getToken(int i);

    public Claims parseToken(String token);
}
