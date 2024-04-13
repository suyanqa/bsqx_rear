package com.bsqx.bsqx_rear.service.Vehicle;

import com.bsqx.bsqx_rear.DTO.Vehicle.Vehicle;
import com.bsqx.bsqx_rear.response.ApiResponse;

import java.util.List;


/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/12 15:25
 * @Author ： SuYan
 * @File ：VehicleService.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
// 车辆服务接口，定义了操作车辆的方法
public interface VehicleService {
    // 是否存在车辆
    Boolean isVehicle(Vehicle vehicle);
    // 添加车辆
    Vehicle addVehicle(Vehicle vehicle);

    // 支持模糊查询的方法
    List<Vehicle> dimSearch(Long id);
    List<Vehicle> dimSearch(String name);

    // 删除车辆
    void deleteVehicle(Long vehicleId);

    // 获取车辆信息
    Vehicle getVehicleById(Long vehicleId);

    boolean isVehicleExists(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehiclesByCustomerId(Long customerId);
}
