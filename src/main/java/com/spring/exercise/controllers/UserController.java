package com.spring.exercise.controllers;

import com.spring.exercise.model.User;
import com.spring.exercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.getAll());
        return "list";
    }

    @GetMapping("/add")
    public String addUser(@RequestParam("imie") String firstName, @RequestParam("nazwisko") String lastName, @RequestParam("wiek")
            Integer age) {

        if (firstName == null || firstName.isEmpty()) {
            return "err";
        }

        userRepository.addUser(new User(firstName, lastName, age));
        return "success";
    }

    @PostMapping("/add")
    public String postUser(@ModelAttribute User user, BindingResult errors) {

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            return "redirect:/err.html";
        }

       //userRepository.addUser(new User(firstName, lastName, age));
        return "redirect:/success.html";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user, BindingResult errors, Model model) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            return "err";
        }
        userRepository.addUser(user);
        return "success";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }
}