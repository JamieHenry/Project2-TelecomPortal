package com.telecom.jwt;

import java.util.Date;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.telecom.beans.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
    
    // 24 hrs
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
    private static final Logger logger = Logger.getLogger(JwtTokenUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
                .setIssuer("JamVan")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT Expired " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token is null, empty, or only whitespace " + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("JWT is invalid " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT is not supported " + e.getMessage());
        } catch (SignatureException e) {
            logger.error("Signature validation failed");
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
