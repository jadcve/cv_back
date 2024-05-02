package com.alain.cv.cv.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alain.cv.cv.entities.ContactData;
import com.alain.cv.cv.entities.User;
import com.alain.cv.cv.repositories.ContactDataRepository;
import com.alain.cv.cv.repositories.UserRepository;
import com.alain.cv.cv.services.ContactDataService;

import jakarta.transaction.Transactional;

@Service
public class ContactDataServiceImp implements ContactDataService{

    @Autowired
    private ContactDataRepository contactDataRepository;

    @Autowired
    private UserRepository userRepository;

    public ContactDataServiceImp(ContactDataRepository contactDataRepository) {
        this.contactDataRepository = contactDataRepository;
    }

    @Transactional
    public ContactData saveContactData(ContactData contactData) {
        if (contactData.getUser() != null && contactData.getUser().getId() != null) {
            User user = userRepository.findById(contactData.getUser().getId())
                                      .orElseThrow(() -> new RuntimeException("User not found with id: " + contactData.getUser().getId()));
            contactData.setUser(user);
        }
        return contactDataRepository.save(contactData);
    }


}
