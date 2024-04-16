package com.bsqx.bsqx_rear.repository.Inventroy;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;
import com.bsqx.bsqx_rear.DTO.Inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/18 16:20
 * @Author ： SuYan
 * @File ：InventoryRepository.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findById(int id);
    @Query("SELECT i FROM Inventory i WHERE i.itemName LIKE %:keyword% OR i.itemType LIKE %:keyword% OR i.brand LIKE %:keyword%")
    List<Inventory> searchKeyWord(String keyword);
}
