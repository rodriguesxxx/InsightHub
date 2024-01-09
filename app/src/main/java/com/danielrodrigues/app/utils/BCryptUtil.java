package com.danielrodrigues.app.utils;

import java.util.Base64;

import javax.crypto.SecretKey;

import com.danielrodrigues.app.dto.BCryptDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class BCryptUtil {
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public BCryptDTO encrypt(String value) {
        String generateToken = Jwts.builder()
                .setSubject(value)
                .signWith(secretKey)
                .compact();
        byte[] secretKeyBytes = secretKey.getEncoded();
        String secret = Base64.getEncoder().encodeToString(secretKeyBytes);
        return new BCryptDTO(generateToken, secret);
    }

    public String decrypt(BCryptDTO bCryptDTO) {
        byte[] decodedSecretKey = Base64.getDecoder().decode(bCryptDTO.secretKey);
        SecretKey secret = Keys.hmacShaKeyFor(decodedSecretKey);
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(bCryptDTO.token)
                .getBody()
                .getSubject();
    }
}
