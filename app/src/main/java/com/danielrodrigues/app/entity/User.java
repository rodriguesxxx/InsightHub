package com.danielrodrigues.app.entity;

import com.danielrodrigues.app.utils.BCryptUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

@Entity
@Table( name="users" )
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "github_token", nullable = false)
    private String token;

    @Column(name = "secret_key", nullable = false)
    private String key;

    public User(String username, String token) {
        this.username = username;
        this.token = token;
        try {
            generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void generateKey() throws Exception {
        byte[] keyRaw = username.getBytes("UTF-8");
        SecretKeySpec keySpec = new SecretKeySpec(keyRaw, "AES");
        this.key = BCryptUtil.encrypt(keySpec, token);
    }
}
