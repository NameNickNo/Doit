package org.DoIT.controller;

import org.DoIT.model.User;
import org.DoIT.service.UserService;
import org.DoIT.util.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/raw")
    @ResponseBody
    public String rest() {
        LocalDateTime time = LocalDateTime.now();
        return String.valueOf(time);
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAll();

        model.addAttribute("users", users);
        return "users-list";
    }

}
