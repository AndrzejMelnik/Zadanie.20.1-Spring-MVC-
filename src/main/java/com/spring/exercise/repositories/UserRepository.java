package com.spring.exercise.repositories;

import com.spring.exercise.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User("Jan", "Kowalski", 30));
        users.add(new User("Jan", "Maciak", 32));
        users.add(new User("Jan", "Kowas", 33));
    }

    public List<User> getAll() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
