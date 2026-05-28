package com.fabricio.personal_finance_api.service;

import java.util.List;
import java.util.Optional;

import com.fabricio.personal_finance_api.dto.UserDTO;
import com.fabricio.personal_finance_api.entity.User;
import com.fabricio.personal_finance_api.repository.UserRepository;
import com.fabricio.personal_finance_api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with id " + id));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        try {
            findById(id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException("Object not found with id " + id);
        }
    }

    public User update(User obj) {
        try {
            User newObj = findById(obj.getId());
            updateData(newObj, obj);
            return repository.save(newObj);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException("Object not found with id " + obj.getId());
        }
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setPassword(obj.getPassword());
        newObj.setRole(obj.getRole());
    }

    public User fromDto(UserDTO objDto) {
        User user = new User();

        user.setId(objDto.getId());
        user.setName(objDto.getName());
        user.setEmail(objDto.getEmail());
        user.setRole(objDto.getRole());

        return user;
    }
}
