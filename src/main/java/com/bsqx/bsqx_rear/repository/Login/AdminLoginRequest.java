package com.bsqx.bsqx_rear.repository.Login;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/4 15:25
 * @Author ： SuYan
 * @File ：AdminLoginRequest.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

/**
 * 该类用于接收用户发送的POST请求的载荷
 * */
public class AdminLoginRequest {

 private String username;
 private String password;
 private String phone;

 // Getters and setters
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
  return "AdminLoginRequest{" +
          "username='" + username + '\'' +
          ", password='" + password + '\'' +
          ", phone='" + phone + '\'' +
          '}';
 }
}
