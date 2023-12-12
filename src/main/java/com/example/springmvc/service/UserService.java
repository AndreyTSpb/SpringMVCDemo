package com.example.springmvc.service;

import com.example.springmvc.dao.UserEntity;
import com.example.springmvc.dao.UserRepository;
import com.example.springmvc.exception.UserNotFoundException;
import com.example.springmvc.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(User user) {
        UserEntity userEntity = new UserEntity(
                null,
                user.getLastName(),
                user.getFirstName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPhone(),
                user.getDepartment_id(),
                user.getRole_id(),
                0L
        );
        userRepository.save(userEntity);
    }

    public void updateUser(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPhone(),
                user.getDepartment_id(),
                user.getRole_id(),
                0L
        );
        userRepository.save(userEntity);
    }

    public void delUser(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPhone(),
                user.getDepartment_id(),
                user.getRole_id(),
                1L
        );
        userRepository.save(userEntity);
    }

    /**
     * Список всех пользователей
     * @return
     */
    public List<User> getAllUsers() {
        Iterable<UserEntity> iterable = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (UserEntity userEntity : iterable){
            users.add(new User(userEntity.getId(),
                    userEntity.getLastName(),
                    userEntity.getFirstName(),
                    userEntity.getPatronymic(),
                    userEntity.getEmail(),
                    userEntity.getPhone(),
                    userEntity.getDepartment_id(),
                    userEntity.getRole_id(),
                    userEntity.getDel())
            );
        }
        return users;
    }

    public User getUserById(Long id){
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: id = " + id));
        return new User(
                userEntity.getId(),
                userEntity.getLastName(),
                userEntity.getFirstName(),
                userEntity.getPatronymic(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.getDepartment_id(),
                userEntity.getRole_id(),
                userEntity.getDel()
        );
    }
}
