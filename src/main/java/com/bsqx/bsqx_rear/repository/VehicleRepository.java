package com.bsqx.bsqx_rear.repository;

import com.bsqx.bsqx_rear.DTO.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/10 22:39
 * @Author ： SuYan
 * @File ：VehicleRepository.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByLicensePlateAndModel(String licensePlate, String model);
}