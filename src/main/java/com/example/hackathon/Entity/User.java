package com.example.hackathon.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "privateToken", nullable = false, length = 512)
    private String privateToken;
    @Column(name = "publicToken", nullable = false, length = 512)
    private String publicToken;

    @Column(name = "clientPublicToken", nullable = false, length = 512)
    private String clientPublicToken;
}
