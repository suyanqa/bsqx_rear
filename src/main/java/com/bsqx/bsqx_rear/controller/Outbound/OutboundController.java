package com.bsqx.bsqx_rear.controller.Outbound;

import com.bsqx.bsqx_rear.DTO.Outbound.Outbound;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.Outbound.OutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/14 15:38
 * @Author ： SuYan
 * @File ：OutboundControllor.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@RestController
@RequestMapping("/outbound")
public class OutboundController {

    private final OutboundService os;

    @Autowired
    public OutboundController(OutboundService os) {
        this.os = os;
    }

    @PostMapping("/add")
    public ApiResponse addOutbound(@RequestBody Outbound outbound) {

        System.out.println(outbound);
        if (outbound.getItemId() == null || outbound.getItemId().trim().isEmpty()) {
            return new ApiResponse<>(false, "输入的ID不符合需求", outbound);
        }

        Outbound addOutbound = os.addOutbound(outbound);
        if (addOutbound != null) {
            return new ApiResponse<>(true, "出库成功", addOutbound);
        } else {
            return new ApiResponse<>(false, "相同配件ID的记录已存在", null);
        }
    }


    @PostMapping("/search")
    public ApiResponse<List<Outbound>> searchOutbound(@RequestBody Map<String, String> requestBody) {
        String keyword = requestBody.get("keyword");
//        System.out.println("出件管理 search接受的参数: "+keyword);
        if (StringUtils.isEmpty(keyword)) {
            return new ApiResponse<>(false, "请输入有效的查询关键字", null);
        }

        List<Outbound> outbounds = os.searchOutbound(keyword);
//        System.out.println(outbounds);
        if (!outbounds.isEmpty()) {
            return new ApiResponse<>(true, "查询成功", outbounds);
        } else {
            return new ApiResponse<>(false, "出库记录", null);
        }
    }

    @PostMapping("/edit/{id}")
    public ApiResponse<Outbound> editOutbound(@PathVariable int id, @RequestBody Outbound outbound) {
        return os.editOutbound(id, outbound);
    }

    @PostMapping("/delete/{id}")
    public ApiResponse<String> deleteOutboundById(@PathVariable int id) {
        return os.deleteOutboundById(id);
    }

    @PostMapping("all")
    public ApiResponse<Outbound> allOutBound() {
        List<Outbound> outbounds = os.allOutBound();
        return new ApiResponse(true,"加载成功",outbounds);
    }
}