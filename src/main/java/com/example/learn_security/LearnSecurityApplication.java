package com.example.learn_security;

import com.example.learn_security.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableConfigurationProperties(value = JwtProperties.class)
public class LearnSecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(LearnSecurityApplication.class, args);
  }

}
