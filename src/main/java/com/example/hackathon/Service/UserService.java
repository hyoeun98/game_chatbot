package com.example.hackathon.Service;

import com.example.hackathon.Dto.Request.AuthRequestDto;
import com.example.hackathon.Dto.Response.AuthResponseDto;
import com.example.hackathon.Dto.Response.CheckResponse;
import com.example.hackathon.Entity.User;
import com.example.hackathon.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public AuthResponseDto register(AuthRequestDto authRequestDto) throws NoSuchAlgorithmException {

//        publicKey 와 privateKey 생성

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(512);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String publicToken = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateToken = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        User user = User.builder()
                .id(authRequestDto.getId())
                .publicToken(publicToken)
                .privateToken(privateToken)
                .clientPublicToken(authRequestDto.getClientPublicKey())
                .build();

        User savedUser = userRepository.save(user);

        return AuthResponseDto.builder()
                .publicToken(savedUser.getPublicToken()).build();
    }
    public CheckResponse checkMe(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return new CheckResponse(true,user.get().getPublicToken());
        }
        return new CheckResponse(false,"");
    }


}
