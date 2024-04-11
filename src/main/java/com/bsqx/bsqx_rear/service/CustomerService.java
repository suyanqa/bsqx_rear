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
    boolean isCustomerExists(Customer customer);
    void addCustomer(Customer customer);
    List<Customer> searchCustomersByUsername(String name);
    List<Customer> getAllCustomers();
    List<Customer> searchCustomersByUsernameContaining(String username); // 声明模糊查询方法
    Customer getCustomerById(Long id);

}
