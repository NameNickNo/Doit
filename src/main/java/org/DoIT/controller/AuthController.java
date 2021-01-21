package org.DoIT.controller;

import org.DoIT.model.User;
import org.DoIT.service.UserService;
import org.DoIT.util.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserValidator userValidator;
    private final UserService userService;

    public AuthController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "auth/sign-up";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/sign-up";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping("/login")
    public String login() {
        return "auth/sign-in";
    }

}
