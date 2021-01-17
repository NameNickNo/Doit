package org.DoIT.dao;

import org.DoIT.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void createUser(User user);

    User getByEmail(String email);
}
