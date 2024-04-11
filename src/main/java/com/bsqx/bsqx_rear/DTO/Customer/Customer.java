package com.bsqx.bsqx_rear.DTO.Customer;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/8 19:07
 * @Author ： SuYan
 * @File ：Customer.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动增长
    private Long id;
    private String name;
    private String contactNumber;
    private String additionalContactNumber;

    public Customer(String name, String contactNumber, String additionalContactNumber, Long id) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.additionalContactNumber = additionalContactNumber;
        this.id = id;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAdditionalContactNumber() {
        return additionalContactNumber;
    }

    public void setAdditionalContactNumber(String additionalContactNumber) {
        this.additionalContactNumber = additionalContactNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", additionalContactNumber='" + additionalContactNumber + '\'' +
                '}';
    }
}
