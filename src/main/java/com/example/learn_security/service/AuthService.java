package com.example.learn_security.service;


import com.example.learn_security.dto.AuthRequest;
import com.example.learn_security.dto.AuthResponse;
import com.example.learn_security.dto.SignupRequest;
import com.example.learn_security.dto.request.UserSingInRequest;

public interface AuthService {

  AuthResponse auth(AuthRequest authRequest);
  AuthResponse loginUser(UserSingInRequest userSingInRequest);

  AuthResponse register(SignupRequest signupRequest);

}
