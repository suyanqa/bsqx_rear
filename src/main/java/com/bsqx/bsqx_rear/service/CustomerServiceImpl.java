package com.bsqx.bsqx_rear.service;

import com.bsqx.bsqx_rear.DTO.Customer;
import com.bsqx.bsqx_rear.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/8 19:14
 * @Author ： SuYan
 * @File ：CustomerServiceImpl.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    // 检查数据库中是否已存在相同的用户
    public boolean isCustomerExists(Customer customer) {
        // 根据姓名和联系方式查询客户
        Customer existingCustomer = customerRepository.findByNameAndContactNumber(customer.getName(), customer.getContactNumber());
        return existingCustomer != null;
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}