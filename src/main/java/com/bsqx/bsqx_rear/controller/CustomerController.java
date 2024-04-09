package com.bsqx.bsqx_rear.controller;

import com.bsqx.bsqx_rear.DTO.Customer;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/8 19:09
 * @Author ： SuYan
 * @File ：CustomerController.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ApiResponse<Void> addCustomer(@RequestBody Customer customer) {
        System.out.println("/customer/add被请求.....");

        // 检查必填字段是否为空
        if (customer.getName() == null || customer.getName().isEmpty()) {
            return new ApiResponse(false, "添加客户失败", "客户姓名不能为空");
        }

        // 检查数据库中是否已存在相同的用户
        if (customerService.isCustomerExists(customer)) {
            return new ApiResponse(false, "添加客户失败", "该客户已存在");
        }

        try {
            // 调用服务层方法，将客户数据插入数据库
            customerService.addCustomer(customer);
            return new ApiResponse(true, "添加客户成功", "恭喜小主又多一位客户");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ApiResponse(false, "添加客户失败", "添加客户时发生错误");
        }
    }
}
