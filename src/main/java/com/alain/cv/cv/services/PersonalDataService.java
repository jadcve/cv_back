package com.alain.cv.cv.services;
import java.util.List;

import com.alain.cv.cv.entities.PersonalData;

public interface PersonalDataService {
    
    PersonalData savePersonalData(PersonalData personalData);
    PersonalData updatePersonalData(PersonalData personalData);
   
    void delete(Long id);
    List<PersonalData> findAllPersonalDataByUserId(Long id);


}
