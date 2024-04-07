package com.bsqx.bsqx_rear.service;

import com.bsqx.bsqx_rear.model.Admin;
import com.bsqx.bsqx_rear.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/4 15:23
 * @Author ： SuYan
 * @File ：AdminService.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

/**
 * AdminService该类提供了validateAdmin方法传入请求提供的用户名和密码进行数据库查询返回true表示成功,false失败
 * */
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean validateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsernameAndPassword(username, password);
        System.out.println(admin);
        return admin != null;
    }
}
