package com.danielrodrigues.app.repository;

import com.danielrodrigues.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
