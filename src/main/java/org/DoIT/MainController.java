package org.DoIT;

import org.DoIT.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/new")
    public String createUser() {
        return "sign-up";
    }

    @PostMapping("/user/new")
    public String signUp(User user) {
        users.add(user);
        return "redirect:/users";
    }
}
