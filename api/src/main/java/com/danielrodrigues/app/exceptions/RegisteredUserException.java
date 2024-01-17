package com.danielrodrigues.app.exceptions;

import org.springframework.http.HttpStatus;

public class RegisteredUserException extends Exception{
    public HttpStatus code;

    public RegisteredUserException() {
        super("Usuario já cadastrado!");
        this.code = HttpStatus.CONFLICT;
    }
    public RegisteredUserException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
