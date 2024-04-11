package com.bsqx.bsqx_rear.service.Vehicle;

import com.bsqx.bsqx_rear.DTO.Vehicle.Vehicle;

import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/10 22:37
 * @Author ： SuYan
 * @File ：VehicleService.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

public interface VehicleService {

    List<Vehicle> getAllVehicles();

    Vehicle getVehicleById(Long id);

    Vehicle addVehicle(Vehicle vehicle);

    void deleteVehicle(Long id);

    Vehicle updateVehicle(Long id, Vehicle vehicle);

    boolean isVehicleExists(Vehicle vehicle); // 添加此方法用于检查车辆是否已存在
}
