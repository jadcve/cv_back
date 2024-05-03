package com.alain.cv.cv.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.alain.cv.cv.entities.ContactData;
import com.alain.cv.cv.entities.PersonalData;
import com.alain.cv.cv.entities.User;
import com.alain.cv.cv.repositories.ContactDataRepository;
import com.alain.cv.cv.repositories.UserRepository;
import com.alain.cv.cv.services.ContactDataService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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

    @Transactional
    public ContactData updateContactData(ContactData cData) {
        ContactData existingData = contactDataRepository.findById(cData.getId())
        .orElseThrow();

        existingData.setEmail(cData.getEmail());
        existingData.setPhone(cData.getPhone());
        existingData.setAddress(cData.getAddress());
        existingData.setGithub(cData.getGithub());
        existingData.setLinkedin(cData.getLinkedin());
        existingData.setWebPage(cData.getWebPage());
 
        return contactDataRepository.save(existingData);
    }

    @Transactional
    public void deleteContactData(Long id) {
        if (!contactDataRepository.existsById(id)) {
            throw new EntityNotFoundException("ContactData not found with id: " + id);
        }
        contactDataRepository.deleteById(id);
    }

    @Transactional
    public List<ContactData> findAllContactDataByUserId(Long userId) {
        return contactDataRepository.findByUserId(userId);
    }



}
