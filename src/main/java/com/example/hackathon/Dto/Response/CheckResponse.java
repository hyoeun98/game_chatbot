package com.example.hackathon.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckResponse {
    private boolean status;
    private String publicToken;
}
