package com.example.hackathon.Dto.Response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class QueryResponseDto {
    private String answer;

    public QueryResponseDto(String answer) {
        this.answer = answer;
    }
}
