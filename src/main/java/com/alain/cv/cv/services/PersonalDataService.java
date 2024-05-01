package com.alain.cv.cv.services;
import com.alain.cv.cv.entities.PersonalData;

public interface PersonalDataService {
    
    PersonalData save(PersonalData personalData);
    PersonalData update(PersonalData personalData);
    PersonalData findById(Long id);
    void delete(Long id);


}
