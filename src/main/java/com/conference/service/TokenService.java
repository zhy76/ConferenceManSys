package com.conference.service;

import com.conference.entity.*;
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
    //public String getToken(Organizer organizer);
    public String getToken(Hotel hotel);
    //将增加用户的人数写入token
    public String getToken(int i);

    public Claims parseToken(String token);

}
