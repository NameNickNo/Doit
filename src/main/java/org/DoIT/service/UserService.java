package org.DoIT.service;

import org.DoIT.model.User;

import java.util.List;

public interface UserService {
    
    List<User> getAll();

    User getByEmail(String email);

    void save(User user);
}
