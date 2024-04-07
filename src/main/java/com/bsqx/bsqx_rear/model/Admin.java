package com.bsqx.bsqx_rear.model;

import jakarta.persistence.*;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/4 15:16
 * @Author ： SuYan
 * @File ：Admin.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String username;
    private String password;
    private String phone;

    public Admin() {
        // Default constructor required by JPA
    }

    public Admin(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    // Getters and setters
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Login接口数据库返回的数据{" +
                "adminId=" + adminId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
