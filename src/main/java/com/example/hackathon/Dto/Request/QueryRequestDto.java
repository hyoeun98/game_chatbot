package com.example.hackathon.Dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QueryRequestDto {

    private String role ;
    @NotEmpty
    private String content;

    public void setRole(String role) {
        this.role = role;
    }
}
