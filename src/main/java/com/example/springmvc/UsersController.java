package com.example.springmvc;

import com.example.springmvc.dao.DepartmentEntity;
import com.example.springmvc.dao.UserEntity;
import com.example.springmvc.dao.UserRepository;
import com.example.springmvc.exception.UserNotFoundException;
import com.example.springmvc.model.Department;
import com.example.springmvc.model.User;
import com.example.springmvc.service.DepartmentService;
import com.example.springmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final DepartmentService departmentService;

    private final String baseUrl = "users";

    @GetMapping
    public String index(Model model) {
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("users", userService.getAllUsers());
        return "users_list";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("user", new UserEntity());

        //Для фомирования списков выбора
        model.addAttribute("optionsDep", listDepartment()); //отделы
        model.addAttribute("optionsRole", listRole()); //роли
        return "add_user";
    }

    /**
     * Выборка существующих отделов
     * @return - асоциативный массив
     */
    private HashMap<String, String> listDepartment(){
        HashMap<String, String> options = new HashMap<>();
        for (Department department : departmentService.getAllDepartmentsNotDel()){
            options.put(String.valueOf(department.getId()), department.getName());
        }
        return options;
    }

    /**
     * Выбора существующих ролей
     * @return - ассоциативный массив
     */
    private HashMap<Long, String> listRole(){
        HashMap<Long, String> options = new HashMap<>();
        options.put(1L, "Сотрудник");
        options.put(2L, "Пользователь");
        options.put(3L, "Дирекция");
        options.put(4L, "Администратор системы");
        return options;
    }

    /**
     * Сохраняем пользовтеля
     * @param user - объект с фрмы
     * @return - редирект
     */
    @PostMapping("/add_user")
    public String greetingSubmit(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/"+baseUrl;
    }

    /**
     * Получить пользователя по его идентификатору
     */
    @GetMapping("/edit/{id}")
    public String editUserById(@PathVariable("id") Long id, Model model){
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("user", userService.getUserById(id));
        //Для фомирования списков выбора
        model.addAttribute("optionsDep", listDepartment()); //отделы
        model.addAttribute("optionsRole", listRole()); //роли
        return "edit_user";
    }

    @PostMapping("/edit_user")
    public String editSubmit(@ModelAttribute User user) {
        if (!userRepository.existsById(user.getId()))
            throw new UserNotFoundException("User not found: id = " + user.getId());
        userService.updateUser(user);
        return "redirect:/"+baseUrl;
    }

    /**
     * Удаление пользователя
     * @param id - идентификатор пользователя
     * @return
     */
    @GetMapping("/del/{id}")
    public String delUser(@PathVariable("id") Long id){
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("User not found: id = " + id);
        User user = userService.getUserById(id);
        userService.delUser(user);
        return "redirect:/" + baseUrl;
    }
}
