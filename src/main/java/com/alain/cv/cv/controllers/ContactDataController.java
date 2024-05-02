package com.alain.cv.cv.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alain.cv.cv.entities.ContactData;
import com.alain.cv.cv.services.impl.ContactDataServiceImp;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contactData")
public class ContactDataController {

    private ContactDataServiceImp contactDataServiceImp;

    public ContactDataController(ContactDataServiceImp contactDataServiceImp) {
        this.contactDataServiceImp = contactDataServiceImp;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveContactData(@Valid @RequestBody ContactData cData, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        
        ContactData contactData = contactDataServiceImp.saveContactData(cData);
        return ResponseEntity.ok(contactData);
    }
   

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }



}
