package com.example.learn_security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Getter
@Configuration
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

  private String secret;

  private Long expiration;

  private Long refreshExpiration;

}
