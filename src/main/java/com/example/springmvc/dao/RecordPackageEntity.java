package com.example.springmvc.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="record_package")
public class RecordPackageEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id //первичный ключ
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    //@MappedCollection(idColumn = "record_package_id")
    @OneToMany(targetEntity = RecordEntity.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<RecordEntity> recordEntities;
}
