package com.example.learn_security.controller;

import com.example.learn_security.dto.AuthRequest;
import com.example.learn_security.dto.AuthResponse;
import com.example.learn_security.dto.SignupRequest;
import com.example.learn_security.dto.request.UserSingInRequest;
import com.example.learn_security.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthService authService;

  @PostMapping(value = "/login")
  public ResponseEntity<AuthResponse> loginUser(@RequestBody UserSingInRequest userSingInRequest) {
    log.debug("User authentication. username: {}", userSingInRequest.getUsername());

    return ResponseEntity.ok(authService.loginUser(userSingInRequest));
  }


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
