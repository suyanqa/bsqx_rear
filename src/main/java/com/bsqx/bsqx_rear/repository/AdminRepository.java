package com.bsqx.bsqx_rear.repository;

import com.bsqx.bsqx_rear.model.Admin;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/4 15:21
 * @Author ： SuYan
 * @File ：AdminRepository.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> { // 将泛型参数替换为实体类类型
    Admin findByUsernameAndPassword(String username, String password);
}
