package com.example.springmvc.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findAllByLastNameContaining(String lastName); //Spring анотация findAll — найти все записи, ByLastName — параметр обрамляется %, SELECT * FROM users WHERE LastName LIKE '%Bloch%';

    Iterable<UserEntity> findAll(Sort first_name);

//    @Query(value = "SELECT u.id AS id,"+
//            " u.first_name AS first_namne,"+
//            " u.last_name AS last_name,"+
//            " u.patronymic AS patronymic,"+
//            " d.name AS department_name"+
//            "  FROM users AS u LEFT JOIN departments d ON u.department_id = d.id",
//            nativeQuery = true
//    )
//    List<UserEntity> findAll(Sort first_name);
}
