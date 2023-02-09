package com.github.vlad.netetskyi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class CarController {
    @GetMapping("/vehicles")
    public String vehicles(Model model) {
        return "vehicles";
    }
}
