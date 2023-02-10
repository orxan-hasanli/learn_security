package com.example.learn_security.service;

import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;


public interface JwtService {

  String extractUsername(String token);

  Claims exteractAllClaims(String token);

  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

  Claims getAllClaimsFromToken(String token);

  String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

  String generateToken(UserDetails userDetails);

  boolean validateToken(String token, UserDetails userDetails);

}
