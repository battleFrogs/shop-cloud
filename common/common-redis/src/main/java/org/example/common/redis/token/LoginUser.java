package org.example.common.redis.token;

import lombok.Data;

@Data
public class LoginUser {

    private Long loginTime;
    private Long expireTime;



}
