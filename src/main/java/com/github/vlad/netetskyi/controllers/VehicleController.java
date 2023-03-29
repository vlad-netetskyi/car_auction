package com.github.vlad.netetskyi.controllers;

import com.github.vlad.netetskyi.models.Vehicle;
import com.github.vlad.netetskyi.repositories.VehicleRepository;
import jakarta.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Base64;
import java.util.Optional;


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

    @GetMapping("/vehicles/add")
    public String getAddVehicles(Model model) {
        return "vehicles-add";
    }

    @PostMapping("/vehicles/add")
    public String AddVehicle(@RequestParam String city, @RequestParam String brand, @RequestParam String model, @RequestParam String type,
                             @RequestParam String year, @RequestParam String fuel, @RequestParam double engineCapacity,
                             @RequestParam String transmission, @RequestParam int seats, @RequestParam long engineMileage,
                             @RequestParam Part car_img, Model mod) throws IOException {
        InputStream fileContent = car_img.getInputStream();
        byte[] fileAsByteArray = IOUtils.toByteArray(fileContent);
        System.out.println("Added car photo with size = " + fileAsByteArray.length);

        Vehicle vehicle = new Vehicle(brand, model, type, Integer.parseInt(year), transmission, fuel, engineCapacity, seats, city,
                fileAsByteArray, engineMileage);

        vehicleRepository.save(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicle/{id}")
    public String vehicleDetails(@PathVariable(value = "id") long id, Model model) {
        if (!vehicleRepository.existsById(id)) {
            return "redirect:/vehicles";
        }
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        ArrayList<Vehicle> res = new ArrayList<>();
        vehicle.ifPresent(res::add);
        model.addAttribute("vehicle", res);
        return "vehicle-details";
    }

    @GetMapping("/vehicle/{id}/edit")
    public String vehicleEdit(@PathVariable(value = "id") long id, Model model) {
        if (!vehicleRepository.existsById(id)) {
            return "redirect:/vehicles";
        }
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        ArrayList<Vehicle> res = new ArrayList<>();
        vehicle.ifPresent(res::add);
        model.addAttribute("vehicle", res);
        return "vehicle-edit";
    }

    @PostMapping("/vehicle/{id}/edit")
    public String updateVehicle(@PathVariable(value = "id") long id, @RequestParam String city, @RequestParam String brand,
                                @RequestParam String model, @RequestParam String type, @RequestParam String year, @RequestParam String fuel,
                                @RequestParam double engineCapacity, @RequestParam String transmission, @RequestParam int seats,
                                @RequestParam long engineMileage, @RequestParam Part car_img, Model mod) throws IOException {
        InputStream fileContent = car_img.getInputStream();
        byte[] fileAsByteArray = IOUtils.toByteArray(fileContent);
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();

        vehicle.setBrand(brand);
        vehicle.setCity(city);
        vehicle.setEngineCapacity(engineCapacity);
        vehicle.setEngineMileage(engineMileage);
        vehicle.setFuel(fuel);
        vehicle.setImg(fileAsByteArray);
        vehicle.setModel(model);
        vehicle.setSeats(seats);
        vehicle.setTransmission(transmission);
        vehicle.setType(type);
        vehicle.setYear(Integer.parseInt(year));

        vehicleRepository.save(vehicle);
        return "redirect:/vehicles";
    }

    @PostMapping("/vehicle/{id}/remove")
    public String removeVehicle(@PathVariable(value = "id") long id, Model mod) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();
        vehicleRepository.delete(vehicle);
        return "redirect:/vehicles";
    }
}
