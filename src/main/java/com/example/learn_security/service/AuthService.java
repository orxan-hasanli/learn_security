package com.example.learn_security.service;


import com.example.learn_security.dto.AuthRequest;
import com.example.learn_security.dto.AuthResponse;
import com.example.learn_security.dto.SignupRequest;

public interface AuthService {

  AuthResponse auth(AuthRequest authRequest);

  AuthResponse register(SignupRequest signupRequest);

}
