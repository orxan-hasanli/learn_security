package com.example.learn_security.service.impl;

import com.example.learn_security.dto.AuthRequest;
import com.example.learn_security.dto.AuthResponse;
import com.example.learn_security.dto.SignupRequest;
import com.example.learn_security.dto.request.UserSingInRequest;
import com.example.learn_security.model.User;
import com.example.learn_security.repo.UserRepository;
import com.example.learn_security.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final JwtServiceImpl jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public AuthResponse auth(AuthRequest authRequest) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        authRequest.getUsername(),
        authRequest.getPassword()
    ));
    var user = userRepository.findByUsername(authRequest.getUsername())
        .orElseThrow(() -> {
          final String exceptionMessage = "User not found";
          log.error(exceptionMessage);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, exceptionMessage);
        });
    var token = jwtService.generateToken(user);
    return AuthResponse.builder().token(token).build();
  }

  public AuthResponse loginUser(UserSingInRequest request) {
    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return null;
  }


  @Override
  public AuthResponse register(SignupRequest signupRequest) {
    var user = User.builder()
        .username(signupRequest.getUsername())
        .password(passwordEncoder.encode(signupRequest.getPassword()))
        .active(true)
        .build();
    userRepository.save(user);
    var token = jwtService.generateToken(user);
    return AuthResponse.builder().token(token).build();
  }

}
