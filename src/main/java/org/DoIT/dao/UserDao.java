package org.DoIT.dao;

import org.DoIT.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;


    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAll() {

        return jdbcTemplate.query("select * from users;", (rs, i) -> {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    public User getByEmail(String email) {
        return jdbcTemplate.query("select * from users where email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void createUser(User user) {
        jdbcTemplate.update("insert into users values (?,?,?)",
                user.getName(), user.getSurname(), user.getEmail());

    }
}
