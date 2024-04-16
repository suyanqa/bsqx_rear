package com.bsqx.bsqx_rear.controller.Inventory;

import com.bsqx.bsqx_rear.DTO.Inventory.Inventory;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.Inventory.InventoryServices;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/18 15:19
 * @Author ： SuYan
 * @File ：InerntoryController.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    public InventoryServices is;

    public InventoryController(InventoryServices is) {
        this.is = is;
    }

    // 添加库存信息的方法
    @PostMapping("/add")
    public ApiResponse addInventory(@RequestBody Inventory it) {

        int id = it.getId();
        ApiResponse<Inventory> request = null;

        if (id < 0) {
            return request = new ApiResponse<>(false, "ID不符合要求", null);
        }

        Inventory inbound = is.addInventory(it);
//        System.out.println(inbound);
        if (inbound != null) {
            return request = new ApiResponse(true, "添加成功", inbound);
        } else {
            return request = new ApiResponse<>(false, "添加失败,该记录已存在", null);
        }
    }

    // 删除库存
    @PostMapping("/delete/{id}")
    public ApiResponse deleteInventory(@PathVariable int id) {
        if (id < 0) {
            return new ApiResponse(false, "ID不符合规范", null);
        }

        Inventory inventory = is.deleteInventory(id);

        if (inventory != null) {
            return new ApiResponse(true, "删除成功ID为%s".formatted(id), inventory);
        } else {
            return new ApiResponse(false,"删除失败,无%s的记录".formatted(id),null);
        }

    }

    // 编辑库存方法
    @PostMapping("/edit/{id}")
    public ApiResponse editInventory(@RequestBody Inventory it){
        int id = it.getId();
        if (id < 0){
            return new ApiResponse(false,"ID不符合规范",null);
        }

        Inventory byIdInventory = is.findById(id);

        if (byIdInventory != null) {
            Inventory inventory = is.editInventory(it);
            return new ApiResponse(true,"编辑成功",inventory);
        }else {
            return new ApiResponse(false,"编辑失败",null);
        }
    }

    @PostMapping("/search")
    public ApiResponse searchInventory(@RequestBody Map<String,String> requestBody){
        String keyword = requestBody.get("keyword");
        if (StringUtils.isEmpty(keyword)) {
            return new ApiResponse<>(false,"请输入有效的查询关键字",null);
        }
        List<Inventory> inventories = is.searchInventory(keyword);

        if (inventories != null) {
            return new ApiResponse(true,"查询成功",inventories);
        }else {
            return new ApiResponse(false,"搜索失败",null);
        }
    }

    @PostMapping("/all")
    public ApiResponse allInventory(){
        return new ApiResponse(true,"获取成功",is.allInventory());
    }
}
