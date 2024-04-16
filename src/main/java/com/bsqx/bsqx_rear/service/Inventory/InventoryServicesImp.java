package com.bsqx.bsqx_rear.service.Inventory;

import com.bsqx.bsqx_rear.DTO.Inventory.Inventory;

import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/18 15:29
 * @Author ： SuYan
 * @File ：InventoryServicesImp.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

public interface InventoryServicesImp {
    Inventory addInventory(Inventory it);
    Inventory deleteInventory(int id);
    Inventory findById(int id);
    Inventory editInventory(Inventory it);
    List<Inventory> allInventory();
    List<Inventory> searchInventory(String keyword);
}
