package com.example.GoCheeta.security;

import com.example.GoCheeta.model.Users;
import com.example.GoCheeta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceUserDetails implements UserDetailsService {
    @Autowired
    UserRepository userRepo;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.map(CustomUserDetails:: new).get();
    }
}
