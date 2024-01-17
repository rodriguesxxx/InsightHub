package com.danielrodrigues.app.dto;

import javax.crypto.SecretKey;

public class BCryptDTO {
    public String token;
    public String secretKey;
    public BCryptDTO(String token, String secretKey) {
        this.token = token;
        this.secretKey = secretKey;
    }
}
