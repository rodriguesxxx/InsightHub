package com.danielrodrigues.app.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.danielrodrigues.app.models.GithubResponse;

@Component
public class RequestGithubApiUtil {
    
    private String baseUrl = "https://api.github.com";
    private RestTemplate restTemplate = new RestTemplate();
    
    private String username;
    private String token;

    public RequestGithubApiUtil() {}
    
    public RequestGithubApiUtil(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValidUsername(String username)
    {
        String endpoint = baseUrl + "/users/" + username;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(endpoint, Object.class);
            return true;
        } catch(Exception e) {
            return false;
        }
        
    }

    public boolean isValidToken(String token) {
        String endpoint = baseUrl + "/user";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                endpoint, 
                HttpMethod.GET, 
                entity, 
                Object.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch(Exception e) {
            return false;
        }
    }

    public int getCommitsCount() {
        String endpoint = baseUrl + "/search/commits?q=author:"+username;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<GithubResponse> response = restTemplate.exchange(
                endpoint, 
                HttpMethod.GET, 
                entity, 
                GithubResponse.class);
            return response.getBody().getTotalCount();
        } catch(Exception e) {
            return 0;
        }
    }
}
