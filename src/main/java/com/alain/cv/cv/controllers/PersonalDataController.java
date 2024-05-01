package com.alain.cv.cv.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alain.cv.cv.entities.PersonalData;
import com.alain.cv.cv.services.impl.PersonalDataServiceImp;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/personalData")
public class PersonalDataController {

    private PersonalDataServiceImp personalDataServiceImp;

    public PersonalDataController(PersonalDataServiceImp personalDataServiceImp) {
        this.personalDataServiceImp = personalDataServiceImp;
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePersonalData(@Valid @RequestBody PersonalData pData, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        
      PersonalData personalData = personalDataServiceImp.save(pData);
        return ResponseEntity.ok(personalData);
    }
    



    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
