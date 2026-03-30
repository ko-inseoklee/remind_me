package com.example.common.infrastructure.config.jwt;

import lombok.Data;

@Data
public class UserJwtClaimDTO {
    Long id;
    String email;
    String nickname;

}
