package com.mikhail.service.impl;

import com.mikhail.errors.CatsNotFoundException;
import com.mikhail.model.User;
import com.mikhail.repository.UserRepository;
import com.mikhail.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findOneByLogin(String login) {
        return userRepository.findOneByLogin(login).orElseThrow(() -> new CatsNotFoundException());
    }
}
