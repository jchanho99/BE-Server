package com.example.writeagain_be.DTO;

import com.example.writeagain_be.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
    private String username;
    private User.BlogType blogType;
}
