package com.example.springmvc.model;

import lombok.Value;

@Value
public class Order {
    Long id;
    String name;
    int del;
}
