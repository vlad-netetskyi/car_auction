package com.github.vlad.netetskyi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;
    private String model;
    private String type;
    private int year;
    // private double price;
    private String transmission;
    private String fuel;
    private double engineCapacity;
    private int seats;
    private String city;
    private byte[] img;
    private long engineMileage;

    public Vehicle(String brand, String model, String type, int year, String transmission, String fuel, double engineCapacity, int seats, String city, byte[] img, long engineMileage) {
        // this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = year;
        //  this.price = price;
        this.transmission = transmission;
        this.fuel = fuel;
        this.engineCapacity = engineCapacity;
        this.seats = seats;
        this.img = img;
        this.city = city;
        this.engineMileage = engineMileage;
    }

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

   /* public double getPrice() {
        return price;
    }*/

    public byte[] getImg() {
        return img;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public String getFuel() {
        return fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public int getSeats() {
        return seats;
    }

    public long getEngineMileage() {
        return engineMileage;
    }

    public String getCity() {
        return city;
    }


    public String getBase64ImgFile() throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encodeBase64(img, false);
        return new String(encodeBase64, StandardCharsets.UTF_8);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year  && Objects.equals(id, vehicle.id) && Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model) && Objects.equals(type, vehicle.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, type, year, engineMileage);
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                ", transmission='" + transmission + '\'' +
                ", fuel='" + fuel + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", seats=" + seats +
                ", city='" + city + '\'' +
                ", img=" + Arrays.toString(img) +
                ", engineMileage=" + engineMileage +
                '}';
    }
}