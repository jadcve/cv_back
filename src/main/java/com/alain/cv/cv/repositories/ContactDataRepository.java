package com.alain.cv.cv.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alain.cv.cv.entities.ContactData;

public interface ContactDataRepository extends CrudRepository<ContactData, Long>{

   List<ContactData> findByUserId(Long userId);

}
