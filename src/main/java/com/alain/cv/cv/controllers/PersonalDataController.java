package com.alain.cv.cv.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alain.cv.cv.entities.PersonalData;
import com.alain.cv.cv.services.impl.PersonalDataServiceImp;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




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
        
        PersonalData personalData = personalDataServiceImp.savePersonalData(pData);
        return ResponseEntity.ok(personalData);
    }

    @PutMapping("update")
    public ResponseEntity<?> putMethodName(@Valid @RequestBody PersonalData pData, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        PersonalData personalData = personalDataServiceImp.updatePersonalData(pData);
        return ResponseEntity.ok(personalData);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<PersonalData>> getAllPersonalDataByUserId(@PathVariable Long userId) {
        List<PersonalData> personalData = personalDataServiceImp.findAllPersonalDataByUserId(userId);
        if (personalData.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(personalData);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deletePersonalData(@PathVariable Long userId) {
        try {
            personalDataServiceImp.delete(userId);
            return ResponseEntity.ok().build();  
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();  
        }
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }



}
