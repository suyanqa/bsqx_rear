package com.bsqx.bsqx_rear.service.Inventory;

import com.bsqx.bsqx_rear.DTO.Inventory.Inventory;
import com.bsqx.bsqx_rear.repository.Inventroy.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/18 15:28
 * @Author ： SuYan
 * @File ：InventoryServices.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@Service
public class InventoryServices implements InventoryServicesImp {

    @Autowired
    private InventoryRepository ir;

    @Override
    public Inventory addInventory(Inventory it) {

        // 查询数据库是否已有该ID存在
        Inventory idInventory = ir.findById(it.getId());

        if (idInventory != null) {
            return null;
        } else {
            return ir.save(it);
        }

    }

    // 删除库存记录
    @Override
    public Inventory deleteInventory(int id) {
        // optional类Java8推出,用于解决空指针异常,该处理方法可以更高效,更安全的处理null的问题
        Optional<Inventory> byInventory = Optional.ofNullable(ir.findById(id));
        // 判断是否包含一个非空对象
        if (byInventory.isPresent()) {
            ir.deleteById(id);
            return byInventory.get();
        }else {
            return null;
        }
    }

    // Services层去调用Repository中的方法
    @Override
    public Inventory findById(int id) {
        return ir.findById(id);
    }

    @Override
    public Inventory editInventory(Inventory it) {
        int id = it.getId();
        Optional<Inventory> byInventory = Optional.ofNullable(ir.findById(id));

        if (byInventory.isPresent()){
            ir.save(it);
            return byInventory.get();
        }else {
            return null;
        }
    }

    /**
     *
     * 先尝试转换为int进行ID搜索,失败后进行itemName&itemType&brand进行搜索
     * */
    @Override
    public List<Inventory> searchInventory(String keyword) {
        try {
            long id = Long.parseLong(keyword);

            Optional<Inventory> optionalInventory = Optional.ofNullable(ir.findById((int) id));
            if (optionalInventory.isPresent()) {
                List<Inventory> inventory = new ArrayList<>();
                inventory.add(optionalInventory.get());
                return inventory;
            }else {
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            // 根据itemName&itemType&brand进行查询
            List<Inventory> inventory = ir.searchKeyWord(keyword);
            return inventory;
        }
    }

    @Override
    public List<Inventory> allInventory() {
        return ir.findAll();
    }
}
