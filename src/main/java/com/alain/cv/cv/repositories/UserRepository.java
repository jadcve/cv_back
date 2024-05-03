package com.alain.cv.cv.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alain.cv.cv.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
   

}
