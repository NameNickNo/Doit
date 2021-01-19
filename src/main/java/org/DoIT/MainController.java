package org.DoIT;

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
    private final UserValidator userValidator;

    public MainController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
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

    @GetMapping("/users/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/users/new")
    public String signUp(@Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "sign-up";
        }
        userService.save(user);
        return "redirect:/users";
    }
}
