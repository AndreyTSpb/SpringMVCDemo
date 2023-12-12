package com.example.springmvc.model;

import lombok.Value;

@Value
public class Department {
    Long id;
    String name;
    String address;
    Long del;
}
