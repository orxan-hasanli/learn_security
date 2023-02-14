package com.example.learn_security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSingInRequest {

    @NotBlank(message = "Istifadəçi adı boş ola bilməz!")
    private String username;

    @NotBlank(message = "Şifrə boş ola bilməz!")
    private String password;
}