package com.conference.service;

import com.conference.entity.Driver;
import com.conference.entity.Fleet;
import com.conference.entity.Organizer;
import com.conference.entity.Participant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;



@Service("TokenService")
public interface TokenService {
    String getToken(Driver driver);
    String getToken(Fleet fleet);
    String getToken(Participant participant);
    String getToken(Organizer organizerForBase);
    //将增加用户的人数写入token

    String getToken(int i);

    Claims parseToken(String token);

}
