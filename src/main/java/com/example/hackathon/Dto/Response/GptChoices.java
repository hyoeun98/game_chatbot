package com.example.hackathon.Dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GptChoices {
    private Integer index;
    private GptBodyData message;
    private String finish_reason;
}
