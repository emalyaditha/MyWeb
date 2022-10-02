package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    public long countById(Integer id);

    Users findByEmailAndPassword(String email, String password);

    Optional<Users> findUserByEmail(String email);


}
