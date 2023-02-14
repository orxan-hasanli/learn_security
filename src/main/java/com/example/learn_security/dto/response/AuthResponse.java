package com.example.learn_security.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 2313923051207105049L;

    private Long id;

    @Builder.Default
    private String type = "Bearer";

    private String token;

    private String refreshToken;

    private List<String> privileges;

}
