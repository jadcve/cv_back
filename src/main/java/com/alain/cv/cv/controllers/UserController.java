package com.alain.cv.cv.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alain.cv.cv.entities.User;
import com.alain.cv.cv.services.impl.UserServiceImp;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/user")

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User  dataUsuario, BindingResult result) {
        log.info("Received user data: {}", dataUsuario); 
        if (result.hasErrors()) {
            return validation(result);
        }
        User registeredUser = userServiceImp.registerUser(dataUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
