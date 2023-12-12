package com.example.springmvc.model;

import lombok.Value;

@Value
public class User {
    Long id;
    String lastName;
    String firstName;
    String patronymic;
    String email;
    String phone;
    Long department_id;
    Long role_id;
    Long del;
}
