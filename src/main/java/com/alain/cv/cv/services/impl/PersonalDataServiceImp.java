package com.alain.cv.cv.services.impl;

import com.alain.cv.cv.entities.PersonalData;
import com.alain.cv.cv.repositories.PersonalDataRepository;
import com.alain.cv.cv.services.PersonalDataService;

import jakarta.transaction.Transactional;

public class PersonalDataServiceImp implements PersonalDataService{

    private PersonalDataRepository personalDataRepository;

    public PersonalDataServiceImp(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Transactional
    @Override
    public PersonalData save(PersonalData personalData) {
        return personalDataRepository.save(personalData);
    }

    @Override
    public PersonalData update(PersonalData personalData) {
        return null;
    }

    @Override
    public PersonalData findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        
    }

}
