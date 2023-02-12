package com.github.vlad.netetskyi.controllers;

import com.github.vlad.netetskyi.models.Vehicle;
import com.github.vlad.netetskyi.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class VehicleController {
    @Autowired                                      //для створення змінної яка зсилається на репозиторій
    private VehicleRepository vehicleRepository;

    @GetMapping("/vehicles")
    public String vehicles(Model model) {
        Iterable<Vehicle> vehicles = vehicleRepository.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicles";
    }
}
