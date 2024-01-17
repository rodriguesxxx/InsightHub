package com.danielrodrigues.app.services;

import com.danielrodrigues.app.entity.User;
import com.danielrodrigues.app.exceptions.RegisteredUserException;
import com.danielrodrigues.app.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    @Autowired
    protected IUserRepository userRepository;
    public void cadastrarUsuario(User user) throws RegisteredUserException {
        boolean canRegisterUser = userRepository.findByUsername(user.getUsername()) == null;
        if(!canRegisterUser) {
            throw new RegisteredUserException();
        }
        userRepository.save(user);
    }

}
