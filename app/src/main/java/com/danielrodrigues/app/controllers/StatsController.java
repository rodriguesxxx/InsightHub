package com.danielrodrigues.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrodrigues.app.dto.BCryptDTO;
import com.danielrodrigues.app.entity.User;
import com.danielrodrigues.app.models.Response;
import com.danielrodrigues.app.models.Stats;
import com.danielrodrigues.app.repository.IUserRepository;
import com.danielrodrigues.app.services.IStatsService;
import com.danielrodrigues.app.utils.BCryptUtil;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    protected IUserRepository userRepository;

    @Autowired
    protected IStatsService statsService;

    @GetMapping("/{username}")
    public ResponseEntity<Response> getStatesByUsername(@PathVariable String username) {

        User user = userRepository.findByUsername(username);
        if(user == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response<>("Usuario não encontrado!", HttpStatus.NOT_FOUND.value()));
        }
        
        statsService.getStats(user);
        
        //TODO: tratar lancamento de excecoes e erros.      
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Estatísticas do usuário " + username, HttpStatus.OK.value()));
    }

}
