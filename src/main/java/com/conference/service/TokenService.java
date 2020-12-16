package com.conference.service;

import com.conference.entity.Driver;
import com.conference.entity.Fleet;
import com.conference.entity.Participant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;



@Service("TokenService")
public interface TokenService {
    public String getToken(Driver driver);
    public String getToken(Fleet fleet);
    public String getToken(Participant participant);
    //将增加用户的人数写入token
    public String getToken(int i);

    public Claims parseToken(String token);

}
