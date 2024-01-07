package com.danielrodrigues.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrodrigues.app.entity.User;
import com.danielrodrigues.app.models.Response;
import com.danielrodrigues.app.utils.RequestGithubApiUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user) {

        if(user.getUsername() == null || user.getToken() == null) {
            return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new Response<>("Informe todos campos!", HttpStatus.UNPROCESSABLE_ENTITY.value()));
        }
        
        boolean isValidUsername = RequestGithubApiUtil.isValidUsername(user.getUsername());        
        boolean isValidToken = RequestGithubApiUtil.isValidToken(user.getToken());

        if(!isValidUsername) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response<>("O username não existe!", HttpStatus.NOT_FOUND.value()));
        }
        
        if(!isValidToken) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response<>("O token não existe!", HttpStatus.NOT_FOUND.value()));
        }
        
        //TODO: salvar usuario no banco
        //TODO: aplicar conceito de upsert
        //TODO: tratar excecoes e erros.
        //TODO: retornar a url de acesso do usuario as stats.
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response<>("Cadastro realizado com sucesso!", HttpStatus.OK.value()));
    }
}
