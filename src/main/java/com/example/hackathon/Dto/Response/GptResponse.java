package com.example.hackathon.Dto.Response;

import com.example.hackathon.Usage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GptResponse {

    private List<GptChoices> choices;
    private Usage usage;

}
