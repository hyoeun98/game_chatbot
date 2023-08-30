package com.example.hackathon.Controller;


import com.example.hackathon.Dto.Request.AuthRequestDto;
import com.example.hackathon.Dto.Response.AuthResponseDto;
import com.example.hackathon.Dto.Response.CheckResponse;
import com.example.hackathon.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthRequestDto authRequestDto) throws NoSuchAlgorithmException {

        return ResponseEntity.ok(userService.register(authRequestDto));

    }


    @CrossOrigin
    @GetMapping("/me")
    public ResponseEntity<CheckResponse> me(@RequestParam String id) {
        return ResponseEntity.ok(userService.checkMe(id));
    }
}
