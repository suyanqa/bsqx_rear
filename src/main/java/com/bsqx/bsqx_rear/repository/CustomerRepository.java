package com.bsqx.bsqx_rear.repository;

import com.bsqx.bsqx_rear.DTO.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/8 19:16
 * @Author ： SuYan
 * @File ：CustomerRepository.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
public interface CustomerRepository  extends JpaRepository<Customer,Long> {
    // 根据姓名和联系方式查找客户
    Customer findByNameAndContactNumber(String name, String contactNumber);
}
