package com.example.springmvc.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class OrderEntity {
    @Id //первичный ключ
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int del;
}
