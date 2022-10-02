package com.example.GoCheeta.service;

import com.example.GoCheeta.controller.UserNotFoundException;
import com.example.GoCheeta.model.Users;
import com.example.GoCheeta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private UserRepository repo;

    public List<Users> listAll() {
        return (List<Users>) repo.findAll();
    }

    public void save(Users user) {
    repo.save(user);
    }

    public Users log(String email, String password) {
        Users user = repo.findByEmailAndPassword(email, password);
        return user;
    }

    public Users get(Integer id) throws UserNotFoundException {
        Optional<Users> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any Driver with ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any Driver with ID:" + id);
        }
        repo.deleteById(id);
    }
}
