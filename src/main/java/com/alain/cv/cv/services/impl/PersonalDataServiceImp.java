package com.alain.cv.cv.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alain.cv.cv.entities.PersonalData;
import com.alain.cv.cv.entities.User;
import com.alain.cv.cv.repositories.PersonalDataRepository;
import com.alain.cv.cv.repositories.UserRepository;
import com.alain.cv.cv.services.PersonalDataService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PersonalDataServiceImp implements PersonalDataService{

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private UserRepository userRepository;

    public PersonalDataServiceImp(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Transactional
    public PersonalData savePersonalData(PersonalData personalData) {
        if (personalData.getUser() != null && personalData.getUser().getId() != null) {
            User user = userRepository.findById(personalData.getUser().getId())
                                      .orElseThrow(() -> new RuntimeException("User not found with id: " + personalData.getUser().getId()));
            personalData.setUser(user);
        }

        return personalDataRepository.save(personalData);
    }

    @Transactional
    public PersonalData updatePersonalData(PersonalData pData) {
        PersonalData existingData = personalDataRepository.findById(pData.getId())
        .orElseThrow();
 
        existingData.setFirstName(pData.getFirstName());
        existingData.setLastName(pData.getLastName());
        existingData.setCountryDocument(pData.getCountryDocument());
        existingData.setDocumentIdentity(pData.getDocumentIdentity());

        return personalDataRepository.save(existingData);
    }

    @Transactional
    public void delete(Long id) {
        if (!personalDataRepository.existsById(id)) {
            throw new EntityNotFoundException("PersonalData not found with id: " + id);
        }
        personalDataRepository.deleteById(id);
    }

    public List<PersonalData> findAllPersonalDataByUserId(Long id) {
        return personalDataRepository.findByUserId(id);
    }


   
    


}
