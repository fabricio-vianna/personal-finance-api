package com.fabricio.personal_finance_api.service;

import com.fabricio.personal_finance_api.entity.User;
import com.fabricio.personal_finance_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }
}
