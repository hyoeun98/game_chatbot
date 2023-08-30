package com.example.hackathon;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Usage {

    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;
}
