package org.DoIT.service;

import org.DoIT.model.User;
import org.DoIT.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
