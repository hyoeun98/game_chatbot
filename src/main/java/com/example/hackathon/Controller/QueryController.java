package com.example.hackathon.Controller;

import com.example.hackathon.Dto.Request.QueryRequestDto;
import com.example.hackathon.Dto.Response.QueryResponseDto;
import com.example.hackathon.Service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QueryController {
    private final QueryService queryService;
    @CrossOrigin
    @PostMapping("/query")
    public ResponseEntity<QueryResponseDto> query(@RequestBody QueryRequestDto queryRequestDto) {
        return queryService.callGptApi(queryRequestDto);
    }

}
