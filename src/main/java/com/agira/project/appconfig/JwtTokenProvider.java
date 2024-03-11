package com.agira.project.appconfig;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

@Component
public class JwtTokenProvider {
    public  String generateToken(Authentication authentication){
        String name = authentication.name();
        Date currentDate= new Date();
        Date expireDate=new Date(currentDate.getTime()+3600000);
       String token = Jwts.builder().setSubject(name).setIssuedAt(currentDate).setExpiration(expireDate)
               .signWith(SignatureAlgorithm.ES256,"WebKey")
               .compact();
       return token;

    }
}
