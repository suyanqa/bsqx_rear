package com.bsqx.bsqx_rear.service.Vehicle;

import com.bsqx.bsqx_rear.DTO.Vehicle.Vehicle;
import com.bsqx.bsqx_rear.repository.Vehicle.VehicleRepository;
import com.bsqx.bsqx_rear.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/12 15:26
 * @Author ： SuYan
 * @File ：VegicleServiceImp.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@Service
public class VehicleServiceImp implements VehicleService {

    private final VehicleRepository vr;

    @Autowired
    public VehicleServiceImp(VehicleRepository vr) {
        this.vr = vr;
    }

    // 查询数据库中是否已有该车辆
    @Override
    public Boolean isVehicle(Vehicle vehicle) {
        // existVehicle查询数据库返回的Vehicle
        List<Vehicle> existVehicle = vr.findByLicensePlate(vehicle.getLicensePlate());
//        System.out.println(existVehicle);
        return existVehicle != null;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vr.save(vehicle);
    }

    @Override
    public List<Vehicle> dimSearch(Long id) {

        if (vr.findByCustomerId(id) != null){
            return vr.findByCustomerId(id);
        }else {
            return null;
        }
    }

    @Override
    public List<Vehicle> dimSearch(String name) {
        if (vr.findByModel(name) != null) {
            return vr.findByModel(name);
        }else if (vr.findByVin(name) != null) {
            return vr.findByVin(name);
        }else if (vr.findByLicensePlate(name) != null) {
            return vr.findByLicensePlate(name);
        }else {
            return null;
        }
    }

    // 删除车辆
    @Override
    public void deleteVehicle(Long vehicleId) {
        vr.deleteById(vehicleId);
    }

    // 获取车辆信息
    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicle = vr.findById(vehicleId);
        return vehicle.orElse(null);
    }

    @Override
    public boolean isVehicleExists(Vehicle vehicle) {
        // 检查数据库中是否存在相同的车辆
        List<Vehicle> existingVehicles = vr.findByLicensePlateAndModelAndVinAndCustomerId(
                vehicle.getLicensePlate(), vehicle.getModel(), vehicle.getVin(), vehicle.getCustomerId());

        // 如果存在相同的车辆，则返回 true
        return existingVehicles != null && !existingVehicles.isEmpty();
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vr.findAll();
    }

    @Override
    public List<Vehicle> getVehiclesByCustomerId(Long customerId) {
        return vr.findByCustomerId(customerId);
    }
}