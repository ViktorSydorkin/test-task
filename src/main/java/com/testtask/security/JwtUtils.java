package com.testtask.security;

import com.testtask.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
  @Value("${token.secret}")
  private String jwtSecret;

  @Value("${token.valid}")
  private int jwtExpirationMs;

  @PostConstruct
  protected void init() {
    jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
  }

  public String generateJwtToken(Authentication authentication) {

    User userPrincipal = (User) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject((userPrincipal.getEmail()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS256, jwtSecret)
        .compact();
  }

  public boolean validateJwtToken(String jwt) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
      return true;
    } catch (MalformedJwtException | IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }

    return false;
  }

  public String getEmailFromJwtToken(String jwt) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
  }
}
