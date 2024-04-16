package com.bsqx.bsqx_rear.service.Outbound;

import com.bsqx.bsqx_rear.DTO.Outbound.Outbound;
import com.bsqx.bsqx_rear.response.ApiResponse;

import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/14 15:42
 * @Author ： SuYan
 * @File ：OutboundServiceImp.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
public interface OutboundServiceImp {
    Outbound addOutbound(Outbound outbound);

    List<Outbound> searchOutbound(String keyword);

    ApiResponse<Outbound> editOutbound(int id, Outbound outbound);

    ApiResponse<String> deleteOutboundById(int id);
}