package com.mikhail.service;

import com.mikhail.model.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    User findOneByLogin(String login);
}
