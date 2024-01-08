package com.danielrodrigues.app.services;

import com.danielrodrigues.app.entity.User;
import com.danielrodrigues.app.exceptions.RegisteredUserException;

public interface IAuthService {
    public void cadastrarUsuario(User user) throws RegisteredUserException;
}
