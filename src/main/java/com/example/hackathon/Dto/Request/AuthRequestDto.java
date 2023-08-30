package com.example.hackathon.Dto.Request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthRequestDto {

    @NotEmpty
    private String id;

    @NotEmpty
    private String clientPublicKey;

}
