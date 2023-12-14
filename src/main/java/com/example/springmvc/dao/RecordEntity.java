package com.example.springmvc.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="records")
public class RecordEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id //первичный ключ
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_package_id")
    @Fetch(FetchMode.JOIN)
    private RecordPackageEntity recordPackageEntity;
}
