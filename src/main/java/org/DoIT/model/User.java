package org.DoIT.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    @NotBlank(message = "Name should be required")
    @Size(min = 2, max = 30, message = "Surname should be normal size")
    private String name;

    @NotBlank(message = "Surname should be required")
    @Size(min = 2, max = 30, message = "Surname should be normal size")
    private String surname;

    @NotBlank(message = "Email should be required")
    @Email
    private String email;

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
