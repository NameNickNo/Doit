package org.DoIT.util;

import org.DoIT.dao.UserDao;
import org.DoIT.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserDao userDao;

    public UserValidator(@Qualifier("jpaUserDao") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userDao.getByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Email is already in use");
        }
    }
}
