package com.example.learn_security.controller;

import com.example.learn_security.dto.AuthRequest;
import com.example.learn_security.dto.AuthResponse;
import com.example.learn_security.dto.SignupRequest;
import com.example.learn_security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> signup(
      @RequestBody SignupRequest signupRequest
  ) {
    return ResponseEntity.ok(authService.register(signupRequest));
  }


  @PostMapping("/signin")
  public ResponseEntity<AuthResponse> signin(
      @RequestBody AuthRequest authRequest
  ) {
    return ResponseEntity.ok(authService.auth(authRequest));
  }

}
