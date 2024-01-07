package com.danielrodrigues.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrodrigues.app.models.Stats;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @GetMapping("/{username}")
    public ResponseEntity<Stats> getStatesByUsername(@PathVariable String username) {
        //TODO: buscar usuario.
        //TODO: fazer requisicoes a API do github.
        //TODO: retornar estatisticas do usuario.
        //TODO: tratar lancamento de excecoes e erros.      
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
