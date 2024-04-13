package com.bsqx.bsqx_rear.controller.Vehicle;

import com.bsqx.bsqx_rear.DTO.Customer.Customer;
import com.bsqx.bsqx_rear.DTO.Vehicle.SearchRequest;
import com.bsqx.bsqx_rear.DTO.Vehicle.Vehicle;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.Customer.CustomerServiceImpl;
import com.bsqx.bsqx_rear.service.Vehicle.VehicleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/12 15:23
 * @Author ： SuYan
 * @File ：VehicleController.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
/**
 * 该类提供了接口车辆管理的接口,增删改查
 * */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired // 自动装配注解
    private VehicleServiceImp vehicleService;

    @Autowired
    private CustomerServiceImpl csi;

    @PostMapping("/add")
    public ApiResponse<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        // 检查必填字段是否为空
        if (vehicle.getCustomerId() == 0 || vehicle.getLicensePlate() == null || vehicle.getVin() == null || vehicle.getModel() == null) {
            return new ApiResponse<>(false, "添加车辆失败，请检查表单是否填写完整", vehicle);
        }

        // 校验数据库中是否已有该车辆
        if (vehicleService.isVehicleExists(vehicle)) {
            System.out.println("Vehicle: "+vehicle + vehicleService.isVehicleExists(vehicle));
            return new ApiResponse<>(false, "添加车辆失败，该车辆已存在", vehicle);
        }

        Long customerId = vehicle.getCustomerId();
        // 检查用户是否存在
        if (!csi.isCustomerID(customerId)) {
            return new ApiResponse<>(false, String.format("ID:%s对应的客户不存在", customerId), vehicle);
        }

        // 查询用户的车辆列表
        List<Vehicle> customerVehicles = vehicleService.getVehiclesByCustomerId(customerId);
        // 检查用户是否已经拥有相同的车辆
        for (Vehicle existingVehicle : customerVehicles) {
            if (existingVehicle.getLicensePlate().equals(vehicle.getLicensePlate()) &&
                    existingVehicle.getModel().equals(vehicle.getModel()) &&
                    existingVehicle.getVin().equals(vehicle.getVin())) {
                return new ApiResponse<>(false, "添加车辆失败，该车辆已存在于该用户名下", vehicle);
            }
        }

        try {
            vehicleService.addVehicle(vehicle);
            return new ApiResponse<>(true, String.format("恭喜ID为:%s又添一辆车", vehicle.getCustomerId()), vehicle);
        } catch (Exception e) {
            System.out.println("添加车辆时发生错误: " + e.getMessage());
            return new ApiResponse(false, "添加车辆失败", "添加车辆失败，服务器开小差了，请稍后再试");
        }
    }


    @PostMapping("/search")
    public ApiResponse<List<Vehicle>> indistinctSearch(@RequestBody SearchRequest searchRequest) {
        // 接收到的请求参数
        String name = searchRequest.getName();
        Long id = searchRequest.getId();
//        System.out.println("name: " + name + "\t" + "id: " + id);

        List<Vehicle> result = null;

        if (name != null) {
            // 根据 name 进行模糊搜索
            result = vehicleService.dimSearch(name);
            return new ApiResponse<>(true, "搜索成功", result);
        }

        if (id != null && id > 0) {
            // 查询客户ID名下的所有车辆信息
            result = vehicleService.getVehiclesByCustomerId(id);
            if (result == null || result.isEmpty()) {
                return new ApiResponse<>(false, "客户ID名下没有车辆信息", null);
            }
            return new ApiResponse<>(true, "搜索成功", result);
        }

        // 如果没有传递有效的查询关键字，则返回错误信息
        return new ApiResponse<>(false, "请输入有效的查询关键字", null);
    }


    // 删除车辆接口
    @PostMapping("/delete/{id}")
    public ApiResponse<Vehicle> deleteVehicle(@PathVariable Long id) {
        // 检查车辆ID是否有效
        if (id == null || id <= 0 ) {
            return new ApiResponse<>(false, "无效的车辆ID", null);
        }

        // 查询要删除的车辆
        Vehicle deletedVehicle = vehicleService.getVehicleById(id);
        if (deletedVehicle == null) {
            return new ApiResponse<>(false, "要删除的车辆不存在", null);
        }

        // 执行删除操作
        try {
            vehicleService.deleteVehicle(id);

            // 更新数据库信息
            List<Vehicle> updatedVehicles = vehicleService.getAllVehicles();

            return new ApiResponse<>(true, "车辆删除成功", deletedVehicle);
        } catch (Exception e) {
            return new ApiResponse<>(false, "无法删除车辆", null);
        }
    }


    @PostMapping("/edit/{customerId}")
    public ApiResponse<Vehicle> editVehicle(@PathVariable Long customerId, @RequestBody Vehicle updatedVehicle) {
        // 检查客户ID是否有效
        if (customerId == null || customerId <= 0) {
            return new ApiResponse<>(false, "无效的客户ID", null);
        }

        // 根据客户ID查询该客户名下的车辆列表
        List<Vehicle> customerVehicles = vehicleService.getVehiclesByCustomerId(customerId);
        if (customerVehicles == null || customerVehicles.isEmpty()) {
            return new ApiResponse<>(false, "未找到客户名下的车辆", null);
        }

        // 遍历客户名下的车辆列表，找到要编辑的车辆
        Vehicle vehicleToEdit = null;
        for (Vehicle vehicle : customerVehicles) {
            if (vehicle.getId().equals(updatedVehicle.getId())) {
                vehicleToEdit = vehicle;
                break;
            }
        }

        // 检查是否找到要编辑的车辆
        if (vehicleToEdit == null) {
            return new ApiResponse<>(false, "未找到要编辑的车辆", null);
        }

        // 将请求载荷中的信息应用到要编辑的车辆对象中
        if (updatedVehicle.getLicensePlate() != null) {
            vehicleToEdit.setLicensePlate(updatedVehicle.getLicensePlate());
        }
        if (updatedVehicle.getModel() != null) {
            vehicleToEdit.setModel(updatedVehicle.getModel());
        }
        if (updatedVehicle.getVin() != null) {
            vehicleToEdit.setVin(updatedVehicle.getVin());
        }

        try {
            // 保存更新后的车辆信息
            Vehicle savedVehicle = vehicleService.addVehicle(vehicleToEdit);
            return new ApiResponse<>(true, "车辆更新成功", savedVehicle);
        } catch (Exception e) {
            return new ApiResponse<>(false, "无法更新车辆", null);
        }
    }


    // 获取所有车辆信息接口
    @PostMapping ("/all")
    public ApiResponse<List<Vehicle>> getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            return new ApiResponse<>(true, "成功获取所有车辆信息", vehicles);
        } catch (Exception e) {
            return new ApiResponse<>(false, "获取所有车辆信息失败", null);
        }
    }

}
