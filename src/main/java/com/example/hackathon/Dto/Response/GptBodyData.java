package com.example.hackathon.Dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GptBodyData {
    private String role;
    private String content;
}
