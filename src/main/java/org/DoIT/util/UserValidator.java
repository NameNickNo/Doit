package org.DoIT.util;

import org.DoIT.model.User;
import org.DoIT.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserServiceImpl userService;

    public UserValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userService.getByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Email is already in use");
        }
    }
}
