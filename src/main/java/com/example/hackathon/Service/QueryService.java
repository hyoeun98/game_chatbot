package com.example.hackathon.Service;

import com.example.hackathon.Dto.Request.GptRequestDto;
import com.example.hackathon.Dto.Request.QueryRequestDto;
import com.example.hackathon.Dto.Response.GptResponse;
import com.example.hackathon.Dto.Response.QueryResponseDto;
import com.example.hackathon.Entity.User;
import com.example.hackathon.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.StandardSocketOptions;
import java.net.URI;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class QueryService {

    private final UserRepository userRepository;
    private final String prefix = "Bearer ";
    @Value("${GPT_APIKEY}")
    private String APIKEY ;
    @Value("${GPT_URL}")
    private String Url;

    public ResponseEntity<QueryResponseDto> callGptApi(QueryRequestDto queryRequestDto) {

        /*Optional<User> user = userRepository.findByPublicToken(Authentication);
        if(!user.isPresent()) {
            return ResponseEntity.badRequest().build();
        }*/
        QueryResponseDto result = new QueryResponseDto("");
        queryRequestDto.setRole("user");
        try {
            // Request Header 생성 //
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization",prefix + APIKEY);
            // body 생성
            GptRequestDto body = new GptRequestDto(List.of(queryRequestDto));

            HttpEntity entity = new HttpEntity<>(body,headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<GptResponse> response = restTemplate.postForEntity(Url,entity,GptResponse.class);
            log.info("Prompt Token Count : " + response.getBody().getUsage().getPrompt_tokens()
                    + "\n Completion Token Count : " + response.getBody().getUsage().getCompletion_tokens()
                    + "\n Total Token Count : " + response.getBody().getUsage().getTotal_tokens());
            result = new QueryResponseDto(response.getBody().getChoices().get(0).getMessage().getContent());
        }catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }finally {
            return ResponseEntity.ok(result);
        }
        
    }
}
