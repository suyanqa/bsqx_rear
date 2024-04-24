package com.bsqx.bsqx_rear.repository.Vehicle;

import com.bsqx.bsqx_rear.DTO.Vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 车辆数据访问层，用于与数据库交互
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // 根据客户ID查找车辆信息
    List<Vehicle> findByCustomerId(Long customerId);

    // 根据车牌号查找车辆信息
    List<Vehicle> findByLicensePlate(String licensePlate);

    // 根据车型查找车辆信息
    List<Vehicle> findByModel(String model);

    // 根据 VIN 码查找车辆信息
    List<Vehicle> findByVin(String vin);

    List<Vehicle> findByLicensePlateAndModelAndVinAndCustomerId(String licensePlate, String model, String vin, Long customerId);


}



