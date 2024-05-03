package com.alain.cv.cv.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alain.cv.cv.entities.PersonalData;

public interface PersonalDataRepository extends CrudRepository<PersonalData, Long>{
    List<PersonalData> findByUserId(Long userId);


}
