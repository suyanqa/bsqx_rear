package com.bsqx.bsqx_rear.controller.inbound;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.InBound.InBoundServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/16 16:21
 * @Author ： SuYan
 * @File ：controller.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@RestController
@RequestMapping("/inbound")
public class InboundController {
    private final InBoundServices is;


    public InboundController(InBoundServices is) {
        this.is = is;
    }

    @PostMapping("/add")
    public ApiResponse addInBound(@RequestBody Inbound ib) {

        if (ib.getItemId() < 0) { // 修改条件为小于0
            return new ApiResponse (false, "输入的ID不符合需求", ib);
        }

        Inbound inbound = is.addInBound(ib);

        if (inbound != null) {
            return new ApiResponse(true,"入库成功",inbound);
        }else {
            return new ApiResponse(false,"相同配件ID已存在",null);
        }
    }

    @PostMapping("/delete/{id}")
    public ApiResponse deleteInBOUND(@PathVariable Long id){
        if (id < 0) {
            return new ApiResponse(false,"ID不符合要求",null);
        }
        Inbound inbound = is.deleteInBound(id);

        if (inbound != null) {
            return new ApiResponse(true,"删除成功",inbound);
        }else {
            return new ApiResponse(false, "没有找到ID为" + id + "的记录", null);
        }

    }

    @PostMapping("/edit")
    public ApiResponse editInBound(@RequestBody Inbound ib) {
        // 从InBound对象中解析得到ID
        int id = ib.getId();
        System.out.println(id+"\t"+ib);
        // 判断ID是否符合规范
        if (id < 0) {
            return new ApiResponse(false, "ID不符合规范", null);
        }
        // 调用服务层编辑方法,返回一个InBound对象
        Inbound inbound = is.editInBound(id, ib);
        // 返回部位null则表示成功
        if (inbound != null) {
            return new ApiResponse(true, "编辑成功", inbound);
            // 否则services会返回null,services会对传过来的ID做一个数据库的校验,如果没有该ID返回null
        } else {
            return new ApiResponse(false, "编辑失败，不存在ID为 " + id + " 的记录", null);
        }
    }


    @PostMapping("/search")
    public ApiResponse<List<Inbound>> searchInBound(@RequestBody Map<String,String> requestBody){
        String keyword = requestBody.get("keyword");
        if (StringUtils.isEmpty(keyword)) {
            return new ApiResponse<>(false,"请输入有效的查询关键字",null);
        }
        List<Inbound> inbounds = is.searchInBound(keyword);
//        System.out.println(inbounds);
        if (!inbounds.isEmpty()){
            return new ApiResponse<>(true,"查询成功",inbounds);
        }else {
            return new ApiResponse<>(false,"搜索失败,请查看日志",null);
        }
    }

    @PostMapping("/all")
    public ApiResponse<List<Inbound>> allInbound(){
        return new ApiResponse<>(true,"请求成功",is.allInbound());
    }

}
