package com.example.hackathon.Dto.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthResponseDto {
    private String publicToken;
}
