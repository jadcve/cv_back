package com.alain.cv.cv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alain.cv.cv.entities.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long>{
    

}
