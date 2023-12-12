package com.example.springmvc;

import com.example.springmvc.dao.UserEntity;
import com.example.springmvc.model.User;
import com.example.springmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class DashBoardController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/add_user")
    public String addStudent(Model model) {
        model.addAttribute("user", new UserEntity());
        return "add_user";
    }

    @PostMapping("/add_user")
    public String greetingSubmit(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
}
