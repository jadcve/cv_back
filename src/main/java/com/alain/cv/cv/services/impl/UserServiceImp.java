package com.alain.cv.cv.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.alain.cv.cv.services.UserService;

import jakarta.transaction.Transactional;
import com.alain.cv.cv.entities.Audit;
import com.alain.cv.cv.entities.User;
import com.alain.cv.cv.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;
  
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserServiceImp(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User registerUser(User dataUsu) {
        User usuario = new User();
        usuario.setEmail(dataUsu.getEmail());
        usuario.setPassword(passwordEncoder.encode(dataUsu.getPassword()));
        usuario.setAudit(new Audit());
        return  userRepository.save(usuario);

      
    }

   

    


   
}
