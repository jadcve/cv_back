package com.alain.cv.cv.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alain.cv.cv.entities.PersonalData;
import com.alain.cv.cv.repositories.PersonalDataRepository;
import com.alain.cv.cv.services.PersonalDataService;

import jakarta.transaction.Transactional;

@Service
public class PersonalDataServiceImp implements PersonalDataService{

    @Autowired
    private PersonalDataRepository personalDataRepository;

    public PersonalDataServiceImp(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Transactional
    @Override
    public PersonalData save(PersonalData personalData) {
        return personalDataRepository.save(personalData);
    }

    @Transactional
    @Override
    public PersonalData update(PersonalData personalData) {
        return null;
    }

    @Transactional
    @Override
    public PersonalData findById(Long id) {
        return null;
    } 
    
    @Transactional
    @Override
    public void delete(Long id) {
        
    }

}
