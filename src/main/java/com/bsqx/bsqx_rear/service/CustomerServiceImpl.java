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
// CustomerServiceImpl.java
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}

