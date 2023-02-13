package com.example.learn_security.filter;

import com.example.learn_security.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    if (!request.getServletPath().contains("public")) {
      var token = getToken(request);
      if (token != null) {
        try {
          var username = jwtService.extractUsername(token);
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          var authorities = userDetails.getAuthorities();
          var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
          //TODO("What is WebAuthenticationDetailsSoruce and Prupose")
          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
          handleException(response, e);
          return;
        }
      }
    }
    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      return authorizationHeader.substring("Bearer ".length());
    }
    return request.getParameter("token");
  }

  private void handleException(HttpServletResponse response, Exception exception) throws IOException {
    log.error("Error logging in {}", exception.getMessage());
    response.setHeader("error", exception.getMessage());
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    Map<String, String> error = new HashMap<>();
    error.put("error_message", exception.getMessage());
    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(), error);
  }
}
