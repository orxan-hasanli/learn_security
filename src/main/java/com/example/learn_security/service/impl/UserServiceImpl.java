package com.example.learn_security.service.impl;

import com.example.learn_security.repo.UserRepository;
import com.example.learn_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;


}
