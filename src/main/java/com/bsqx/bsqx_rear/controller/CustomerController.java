package com.bsqx.bsqx_rear.controller;
import org.springframework.util.StringUtils;
import com.bsqx.bsqx_rear.DTO.Customer;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // 添加客户路径 /add
    @PostMapping("/add")
    public ApiResponse<Void> addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);

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

    // 根据用户名查询客户信息接口，支持模糊查询
    @PostMapping("/search")
    public ApiResponse<List<Customer>> searchCustomersByName(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        // 如果接收到的 name 为空，直接返回空结果
        if (StringUtils.isEmpty(name)) {
            return new ApiResponse<>(false, "请输入有效的查询关键字", null);
        }

        // 在客户服务中执行模糊查询
        List<Customer> customers = customerService.searchCustomersByUsernameContaining(name);
        if (!customers.isEmpty()) {
            return new ApiResponse<>(true, "查询成功", customers);
        } else {
            return new ApiResponse<>(false, "未找到匹配的客户信息", null);
        }
    }

    // 获取所有用户接口
    @PostMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // 根据id返回客户
    @PostMapping("/{id}")
    public ApiResponse<Customer> getIdCustomers(@PathVariable Long id) {
        // 这里可以根据id从数据库中获取客户信息
        Customer customer = customerService.getCustomerById(id);

        if (customer != null) {
            return new ApiResponse<>(true, "查询客户成功", customer);
        } else {
            return new ApiResponse<>(false, "未找到指定ID的客户", null);
        }
    }

    // 编辑客户信息接口
    @PostMapping("/edit/{customerId}")
    public ApiResponse<Void> editCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        // 根据customerId检查客户是否存在
        Customer existingCustomer = customerService.getCustomerById(customerId);
        if (existingCustomer == null) {
            return new ApiResponse<>(false, "客户不存在", null);
        }

        // 更新客户信息
        existingCustomer.setName(customer.getName());
        existingCustomer.setContactNumber(customer.getContactNumber());

        System.out.println(customer.getAdditionalContactNumber());

        // 检查 additionalContactNumber 是否为 null
        if (customer.getAdditionalContactNumber() != null) {
            existingCustomer.setAdditionalContactNumber(customer.getAdditionalContactNumber());
        }

        // 保存更新后的客户信息
        customerService.addCustomer(existingCustomer);

        return new ApiResponse(true, "编辑成功", this.getIdCustomers(customerId));
    }



}