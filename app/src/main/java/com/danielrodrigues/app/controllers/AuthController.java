package com.danielrodrigues.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrodrigues.app.entity.User;
import com.danielrodrigues.app.models.Response;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/register")
    public ResponseEntity<Response> register(User user) {

        if(user.getUsername() == null || user.getToken() == null) {
            return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new Response<>("Informe todos campos!", HttpStatus.UNPROCESSABLE_ENTITY.value()));
        }
        
        //TODO: fazer requisicao ao github para validar username e token
        //TODO: salvar usuario no banco
        //TODO: aplicar conceito de upsert
        //TODO: tratar excecoes e erros.
        //TODO: retornar a url de acesso do usuario as stats.
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response<>("Cadastro realizado com sucesso!", HttpStatus.OK.value()));
    }
}
