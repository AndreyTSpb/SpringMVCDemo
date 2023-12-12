package com.example.springmvc.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findAllByLastNameContaining(String lastName); //Spring анотация findAll — найти все записи, ByLastName — параметр обрамляется %, SELECT * FROM users WHERE LastName LIKE '%Bloch%';
}
