package com.example.springmvc.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {
    List<DepartmentEntity> findAllByNameContaining(String name);
    List<DepartmentEntity> findAllByDel(int del);
}
