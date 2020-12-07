package com.conference.service.imp;

import com.conference.entity.Driver;
import com.conference.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("TokenService")
public class TokenServiceImp implements TokenService {
    private static final String SECRET = "asfdsfadsfLMNQNQJQKdfkjsdrow32234545fdffdhgdhfgdhgfdhgfdhfgdhfgdh";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static int expiration = 3600 * 2;

    //登录成功后，将用户的用户名和用户类型写入token
    public String getToken(Driver driver){
        String token = "";
        token = Jwts.builder()
                .claim("timeExpiration", new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("userName", driver.getDriverName())
                .signWith(signatureAlgorithm, SECRET)
                .compact();
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

    //    public List<String> getToken(List<Column> columns){
//        List<String> tokenList=new ArrayList<>();
//        for(int i=0;i<columns.size();i++){
//            String token="";
//            System.out.println(columns.get(i).getColumnName());
//            token+=Jwts.builder()
//                    .claim("timeExpiration", new Date(System.currentTimeMillis() + expiration * 1000))
//                    .claim("columnId"+i,columns.get(i).getColumnId())
//                    .claim("columnName"+i,columns.get(i).getColumnName())
//                    .signWith(signatureAlgorithm, SECRET)
//                    .compact();
//            tokenList.add(token);
//        }
//        return tokenList;
//
//    }
    public Claims parseToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
//        Date timeExpiration = new Date((long)claims.get("timeExpiration"));
//        String userName = (String) claims.get("userName");
//        int type = (int) claims.get("type");
        return claims;
    }
}
