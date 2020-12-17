package com.conference.service.impl;

import com.conference.entity.*;
import com.conference.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("TokenService")
public class TokenServiceImpl implements TokenService {
    private static final String SECRET = "asfdsfadsfLMNQNQJQKdfkjsdrow32234545fdffdhgdhfgdhgfdhgfdhfgdhfgdh";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static int expiration = 3600 * 2;

    /**
     * 登录成功后，将用户的用户id写入,
     * driver
     */

    public String getToken(Driver driver){
        String token = "";
        token = Jwts.builder()
                .claim("timeExpiration", new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("driverId", driver.getDriverId())
                .signWith(signatureAlgorithm, SECRET)
                .compact();
        System.out.println(token);
        return token;
    }
    /**
     * 登录成功后，将用户的用户id写入,
     * fleet
     */
    public String getToken(Fleet fleet){
        String token = "";
        token = Jwts.builder()
                .claim("timeExpiration", new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("fleetId", fleet.getFleetId())
                .signWith(signatureAlgorithm, SECRET)
                .compact();
        System.out.println(fleet.getFleetId());
        System.out.println(token);
        return token;
    }

    //登录成功后，将用户的用户名和用户类型写入token
    public String getToken(Participant participant){
        String token = "";
        token = Jwts.builder()
                .claim("timeExpiration", new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("participantId", participant.getParticipantId())
                .signWith(signatureAlgorithm, SECRET)
                .compact();
        System.out.println(participant.getParticipantId());
        System.out.println(token);
        return token;
    }
    public String getToken(Hotel hotel){
        String token = "";
        token = Jwts.builder()
                .claim("timeExpiration", new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("hotelId", hotel.getHotelId())
                .signWith(signatureAlgorithm, SECRET)
                .compact();
        System.out.println(hotel.getHotelId());
        System.out.println(token);
        return token;
    }
    /**
     * 登录成功后，将用户的用户id写入,
     * driver
     */
    public String getToken(Organizer organizer){
        String token = "";
        token = Jwts.builder()
                .claim("timeExpiration", new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("organizerId", organizer.getOrganizerId())
                .signWith(signatureAlgorithm, SECRET)
                .compact();
        System.out.println(token);
        return token;
    }
    //将增加用户的人数写入token
    public String getToken(int i){
        String token="";
        token=Jwts.builder()
                .claim("timeExpiration", new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("addUser",i)
                .signWith(signatureAlgorithm, SECRET)
                .compact();
        return token;
    }


    public Claims parseToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
