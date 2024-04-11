package com.bsqx.bsqx_rear.controller.Vehicle;

import com.bsqx.bsqx_rear.DTO.Vehicle.Vehicle;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.Vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/10 22:38
 * @Author ： SuYan
 * @File ：VehicleController.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/")
    public ApiResponse<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return new ApiResponse<>(true, "获取所有车辆成功", vehicles);
    }

    @PostMapping("/{id}")
    public ApiResponse<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle != null) {
            return new ApiResponse<>(true, "查询车辆成功", vehicle);
        } else {
            return new ApiResponse<>(false, "未找到指定ID的车辆", null);
        }
    }

    @PostMapping("/add")
    public ApiResponse<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        // 检查是否存在相同的车辆
        if (vehicleService.isVehicleExists(vehicle)) {
            return new ApiResponse(false, "添加车辆失败", "该车辆已存在");
        }

        Vehicle addedVehicle = vehicleService.addVehicle(vehicle);
        return new ApiResponse<>(true, "添加车辆成功", addedVehicle);
    }


    @PostMapping("/delete/{id}")
    public ApiResponse<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return new ApiResponse<>(true, "删除车辆成功", null);
    }

    @PostMapping("/update/{id}")
    public ApiResponse<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        if (updatedVehicle != null) {
            return new ApiResponse<>(true, "更新车辆成功", updatedVehicle);
        } else {
            return new ApiResponse<>(false, "未找到指定ID的车辆", null);
        }
    }
}
