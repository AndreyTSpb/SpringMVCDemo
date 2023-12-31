package com.example.springmvc.dao;
import com.example.springmvc.model.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {
    @Id //первичный ключ
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String email;
    private String phone;
    private Long department_id;
    private Long role_id;
    private Long del;
}
