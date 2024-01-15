package com.danielrodrigues.app.controllers;

import com.danielrodrigues.app.exceptions.RegisteredUserException;
import com.danielrodrigues.app.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrodrigues.app.dto.UserDTO;
import com.danielrodrigues.app.entity.User;
import com.danielrodrigues.app.models.Response;
import com.danielrodrigues.app.utils.BaseUrlUtil;
import com.danielrodrigues.app.utils.RequestGithubApiUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    protected IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UserDTO userDTO) {
        try {
            if(userDTO.username() == null || userDTO.token() == null) {
                return ResponseEntity
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(new Response<>("Informe todos campos!", HttpStatus.UNPROCESSABLE_ENTITY.value()));
            }

            boolean isValidUsername = RequestGithubApiUtil.isValidUsername(userDTO.username());
            boolean isValidToken = RequestGithubApiUtil.isValidToken(userDTO.token());

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
            User user = new User(userDTO.username(), userDTO.token());
            authService.cadastrarUsuario(user);
            
            //TODO: retornar a url de acesso do usuario as stats.
            String statsUrl = BaseUrlUtil.getUrl() + "/stats/" + user.getUsername();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response<>("Cadastro realizado com sucesso!", HttpStatus.OK.value(), statsUrl));
        
        } catch (RegisteredUserException e) {
           return ResponseEntity
                   .status(e.code)
                   .body(new Response<>(e.getMessage(), e.code.value()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(new Response<>("Erro interno ao cadastrar usuario!", HttpStatus.BAD_REQUEST.value()));
        }

    }
}
