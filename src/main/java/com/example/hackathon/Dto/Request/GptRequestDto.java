package com.example.hackathon.Dto.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@NoArgsConstructor
public class GptRequestDto {


    private String model = "ft:gpt-3.5-turbo-0613:personal::7szXPes5";
    private List<QueryRequestDto> messages;

    public GptRequestDto(List<QueryRequestDto> messages) {
        this.messages = messages;
    }
}
