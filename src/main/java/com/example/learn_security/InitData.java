package com.example.learn_security;

import com.example.learn_security.model.User;
import com.example.learn_security.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    User yusif = User.builder()/*.id(1L)*/.username("yusif")
        .password(passwordEncoder.encode("test"))
        .build();
   userRepository.save(yusif);
  }


}
