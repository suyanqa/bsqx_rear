package com.bsqx.bsqx_rear.controller.Login;

import com.bsqx.bsqx_rear.repository.Login.AdminLoginRequest;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.Login.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/4 15:24
 * @Author ： SuYan
 * @File ：LoginController.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final AdminService adminService;

    @Autowired
    public LoginController(AdminService adminService) {
        this.adminService = adminService;
    }

    @CrossOrigin(origins = "http://locathost/:3000")
    @PostMapping
    public ApiResponse<String> login(@RequestBody AdminLoginRequest request) {
//        System.out.println(request);
        boolean isValid = adminService.validateAdmin(request.getUsername(), request.getPassword());
//        System.out.println("数据库比对结果:"+isValid);
        if (isValid) {
            // 登录成功
            return new ApiResponse<>(true, "登录成功", "欢迎小主回家");
        } else {
            // 登录失败
            return new ApiResponse<>(false, "登录失败", "请检查用户名或密码错误");
        }
    }

}
