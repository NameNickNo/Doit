package org.DoIT;

import org.DoIT.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    List<User> users = new ArrayList<>();

    @GetMapping("/get")
    public String view(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name, Model model) {
        model.addAttribute("message", "Hello " + name);
        return "index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String rest() {
        LocalDateTime time = LocalDateTime.now();
        return String.valueOf(time);
    }

    @GetMapping("/users")
    public String getUsers(Model model) {

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
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }
        users.add(user);
        return "redirect:/users";
    }
}
