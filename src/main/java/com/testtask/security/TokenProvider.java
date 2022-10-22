package com.testtask.security;

import com.testtask.exception.TokenException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {
  @Value("${token.pass}")
  private String password;

  @Value("${token.valid}")
  private long valid;

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder passwordEncoder;

  @PostConstruct
  protected void init() {
    password = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
  }

  public String createToken(String username) {
    Claims claims = Jwts.claims().setSubject(username);
    Date now = new Date();
    Date validity = new Date(now.getTime() + valid);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, password)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(password).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest httpServletRequest) {
    String bearerToken = httpServletRequest.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(password).parseClaimsJws(token);

      return !claims.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException e) {
      throw new TokenException("JWT token is expired or invalid");
    }
  }
}
