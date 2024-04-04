package com.agira.project.appconfig;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private String jwtSecret ="d22da6ab7dc87f033c6a73e1162cca3eb87778f6c52af66aa1a9a99aa1a95bf9";


    private long jwtExpiration =8600000;


    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() +jwtExpiration);

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(key())
                .compact();
    }

    private Key key() {
        byte[] bytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String userName(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;


        } catch (ExpiredJwtException | IllegalArgumentException | SignatureException | MalformedJwtException exception) {
            throw new RuntimeException(exception);
        }
    }


}
