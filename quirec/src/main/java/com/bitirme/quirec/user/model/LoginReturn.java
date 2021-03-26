package com.bitirme.quirec.user.model;

import lombok.Data;

@Data
public class LoginReturn {

    private String accessToken;

    private Long userId;

}
