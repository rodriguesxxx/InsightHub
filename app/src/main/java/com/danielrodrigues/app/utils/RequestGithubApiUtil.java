package com.danielrodrigues.app.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RequestGithubApiUtil {
    
    private static String baseUrl = "https://api.github.com";
    private static RestTemplate restTemplate = new RestTemplate();

    public static boolean isValidUsername(String username)
    {
        String endpoint = baseUrl + "/users/" + username;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(endpoint, Object.class);
            return true;
        } catch(Exception e) {
            return false;
        }
        
    }

    public static boolean isValidToken(String token) {
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
}
