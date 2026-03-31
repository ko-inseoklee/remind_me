package com.example.remind_me_server.auth.application.dto;

import lombok.Data;

@Data
public class UserJwtClaimDTO {
    Long id;
    String email;
    String nickname;

}
