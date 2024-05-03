package com.alain.cv.cv.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alain.cv.cv.entities.ContactData;
import com.alain.cv.cv.entities.PersonalData;
import com.alain.cv.cv.services.impl.ContactDataServiceImp;


import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


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

    @PutMapping("/update")
    public ResponseEntity<?> updateContactData(@Valid @RequestBody ContactData cData, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        ContactData contactData = contactDataServiceImp.updateContactData(cData);
        return ResponseEntity.ok(contactData);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<ContactData>> getAllContactDataByUserId(@PathVariable Long userId) {
        List<ContactData> contactData = contactDataServiceImp.findAllContactDataByUserId(userId);
        if (contactData.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contactData);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteContactData(@RequestBody Map<String, Long> body) {
        Long id = body.get("id");
        contactDataServiceImp.deleteContactData(id);
        return ResponseEntity.ok().build();
    }
    

    
   

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }



}
