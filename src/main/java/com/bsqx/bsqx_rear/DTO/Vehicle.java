package com.bsqx.bsqx_rear.DTO;

import jakarta.persistence.*;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/10 22:32
 * @Author ： SuYan
 * @File ：Vehicle.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "license_plate")
    private String licensePlate;

    private String model;

    private String vin;

    public Vehicle() {
    }

    public Vehicle(Long customerId, String licensePlate, String model, String vin) {


        this.customerId = customerId;


        this.licensePlate = licensePlate;
        this.model = model;


        this.vin = vin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", licensePlate='" + licensePlate + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}