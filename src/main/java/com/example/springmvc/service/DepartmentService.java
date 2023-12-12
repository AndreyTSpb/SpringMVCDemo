package com.example.springmvc.service;

import com.example.springmvc.dao.DepartmentEntity;
import com.example.springmvc.dao.DepartmentRepository;
import com.example.springmvc.exception.DepartmentNotFoundException;
import com.example.springmvc.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        Iterable<DepartmentEntity> iterable = departmentRepository.findAll();
        ArrayList<Department> departments = new ArrayList<>();
        for(DepartmentEntity departmentEntity : iterable){
            departments.add(new Department(
                    departmentEntity.getId(),
                    departmentEntity.getName(),
                    departmentEntity.getAddress(),
                    departmentEntity.getDel()
            ));
        }
        return  departments;
    }

    public List<Department> getAllDepartmentsNotDel(){
        Iterable<DepartmentEntity> iterable = departmentRepository.findAllByDel(0);
        ArrayList<Department> departments = new ArrayList<>();
        for(DepartmentEntity departmentEntity : iterable){
            departments.add(new Department(
                    departmentEntity.getId(),
                    departmentEntity.getName(),
                    departmentEntity.getAddress(),
                    departmentEntity.getDel()
            ));
        }
        return  departments;
    }
    public Department getDepartmentById(Long id){
        DepartmentEntity departmentEntity = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found: id = " + id));
        return new Department(
                departmentEntity.getId(),
                departmentEntity.getName(),
                departmentEntity.getAddress(),
                departmentEntity.getDel()
        );
    }

    public void saveDepartment(Department department) {
        DepartmentEntity departmentEntity =  new DepartmentEntity(
                null,
                department.getName(),
                department.getAddress(),
                0L
        );
        departmentRepository.save(departmentEntity);
    }

    public void updateDepartment(Department department){
        DepartmentEntity departmentEntity =new DepartmentEntity(
                department.getId(),
                department.getName(),
                department.getAddress(),
                0L
        );
        departmentRepository.save(departmentEntity);
    }

    public void delDepartment(Department department){
        DepartmentEntity departmentEntity = new DepartmentEntity(
                department.getId(),
                department.getName(),
                department.getAddress(),
                1L);
        departmentRepository.save(departmentEntity);
    }
}
