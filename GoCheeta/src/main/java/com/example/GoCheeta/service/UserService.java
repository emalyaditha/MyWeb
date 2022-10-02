package com.example.GoCheeta.service;

import com.example.GoCheeta.controller.UserNotFoundException;
import com.example.GoCheeta.model.Users;
import com.example.GoCheeta.repository.LoginRepository;
import com.example.GoCheeta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private LoginRepository repo1;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Users> listAll() {
        return (List<Users>) repo.findAll();
    }

    public void save(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        repo.save(users);
    }
    public List<Users> lisAll(String keyword) {
        if (keyword != null) {
            return repo1.search(keyword);

        }else{
            return listAll();
        }
    }


    public Users log(String email, String password) {

        Users user = repo1.findByEmailAndPassword(email, password);
        return user;
    }

    public Users name(String email) {
        Users user = repo1.findUserByEmail(email);
        return user;
    }
    public Users get(Integer id) throws UserNotFoundException {
        Optional<Users> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID:" + id);
        }
        repo.deleteById(id);
    }
}

