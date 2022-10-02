package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface LoginRepository extends JpaRepository<Users, Long> {
        Users findByEmailAndPassword(String email, String password);

        Users findUserByEmail(String email);

        @Query(value = "SELECT * FROM users WHERE branch = ?1", nativeQuery = true)
        List<Users> search(String keyword);
    }
