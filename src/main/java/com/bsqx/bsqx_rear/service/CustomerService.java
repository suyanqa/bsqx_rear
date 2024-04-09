package com.bsqx.bsqx_rear.service;

import com.bsqx.bsqx_rear.DTO.Customer;

import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/8 19:12
 * @Author ： SuYan
 * @File ：CustomerService.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
public interface CustomerService {
    List<Customer> searchCustomersByUsername(String name);

    // 检查数据库中是否已存在相同的用户
    boolean isCustomerExists(Customer customer);

    // 添加客户
    void addCustomer(Customer customer);
}
