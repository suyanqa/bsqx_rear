package com.bsqx.bsqx_rear.service.Outbound;

import com.bsqx.bsqx_rear.DTO.Outbound.Outbound;
import com.bsqx.bsqx_rear.repository.Outbound.OutboundRepository;
import com.bsqx.bsqx_rear.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/14 15:41
 * @Author ： SuYan
 * @File ：OutboundService.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

@Service
public class OutboundService implements OutboundServiceImp {

    private final OutboundRepository outboundRepository;

    @Autowired
    public OutboundService(OutboundRepository outboundRepository) {
        this.outboundRepository = outboundRepository;
    }

    @Override
    public Outbound addOutbound(Outbound outbound) {
        int itemId = outbound.getItemId();
        // 检查数据库中是否存在相同配件ID的记录
        Outbound existingOutbound = outboundRepository.findByItemId(itemId);
        if (existingOutbound != null) {
            // 如果存在，则返回null，表示添加失败
            return null;
        }
        // 如果不存在相同配件ID的记录，则执行添加操作
        return outboundRepository.save(outbound);
    }

    @Override
    public List<Outbound> searchOutbound(String keyword) {
        try {
            // 尝试解析关键字为数字，如果成功则按 ID 进行搜索
            int id = Integer.parseInt(keyword);
            return outboundRepository.searchOutboundsByKeywordOrId(null, null, null, id);
        } catch (NumberFormatException e) {
            try {
                // 尝试将关键字解析为日期
                Date date = parseDate(keyword);
//                System.out.println("转换后的日期时间为: " + date);

                // 设置结束日期为当天的午夜
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                java.sql.Date endDate = new java.sql.Date(date.getTime());


                return outboundRepository.searchOutboundsByKeywordOrId(null, date, endDate, 0);

            } catch (ParseException ex) {
//                System.out.println("日期解析失败：" + ex.getMessage());
                // 解析失败，继续按 item_name、brand 和 outbound_data 进行搜索
            } catch (Exception ex) {
                System.out.println("其他异常：" + ex.getMessage());
            }
        }
        // 按 item_name、brand 和 outbound_data 进行搜索
        return outboundRepository.searchOutboundsByKeywordOrId(keyword, null, null, 0);
    }


    // 解析日期字符串的方法
    private Date parseDate(String keyword) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(keyword);
        return new Date(utilDate.getTime());
    }

    @Override
    public ApiResponse<Outbound> editOutbound(int id, Outbound outbound) {
        Optional<Outbound> existingOutboundOptional = outboundRepository.findById(id);
        if (existingOutboundOptional.isPresent()) {
            Outbound existingOutbound = existingOutboundOptional.get();
            existingOutbound.setItemId(outbound.getItemId());
            existingOutbound.setItemName(outbound.getItemName());
            existingOutbound.setBrand(outbound.getBrand());
            existingOutbound.setQuantity(outbound.getQuantity());

            // 检查日期格式是否正确
            try {
                Date outboundDate = parseDate(outbound.getOutboundDate());
                existingOutbound.setOutboundDate(outboundDate);
            } catch (ParseException e) {
                return new ApiResponse<>(false, "日期格式不正确，请使用yyyy-MM-dd格式", null);
            }

            Outbound editedOutbound = outboundRepository.save(existingOutbound);
            return new ApiResponse<>(true, "出库记录编辑成功", editedOutbound);
        } else {
            return new ApiResponse<>(false, "找不到要编辑的出库记录", null);
        }
    }

    @Override
    public ApiResponse<String> deleteOutboundById(int id) {
        Optional<Outbound> existingOutboundOptional = outboundRepository.findById(id);
        if (existingOutboundOptional.isPresent()) {
            outboundRepository.deleteById(id);
            return new ApiResponse<>(true, "成功删除出库记录", null);
        } else {
            return new ApiResponse<>(false, "未找到要删除的出库记录", null);
        }
    }
}