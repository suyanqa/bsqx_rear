package com.bsqx.bsqx_rear.response;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/4 15:52
 * @Author ： SuYan
 * @File ：ApiResponse.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    // Constructors
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

