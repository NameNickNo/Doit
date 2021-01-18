package org.DoIT;

import org.DoIT.dao.UserDao;
import org.DoIT.model.User;
import org.DoIT.util.UserValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {

    private final UserDao userDao;
    private final UserValidator userValidator;

    public MainController(@Qualifier("jpaUserDao") UserDao userDao, UserValidator userValidator) {
        this.userDao = userDao;
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
        List<User> users = userDao.getAll();

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
        userDao.createUser(user);
        return "redirect:/users";
    }
}
