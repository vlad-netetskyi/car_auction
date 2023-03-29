package com.github.vlad.netetskyi.controllers;

import com.github.vlad.netetskyi.models.Vehicle;
import com.github.vlad.netetskyi.repositories.UserRepository;
import com.github.vlad.netetskyi.repositories.VehicleRepository;
import com.github.vlad.netetskyi.services.security.Role;
import com.github.vlad.netetskyi.services.security.User;
import jakarta.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class UserController {
    @Autowired                                      //для створення змінної яка зсилається на репозиторій
    private UserRepository userRepository;

    @GetMapping("/register")
    public String registerView(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,
                           @RequestParam String email, @RequestParam String mobileNumber, Model model) throws IOException {
        User user = new User(name, password, Role.USER, firstName, lastName, email, mobileNumber);
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login( Model model) throws IOException {
        return "redirect:/vehicles";
    }
}
