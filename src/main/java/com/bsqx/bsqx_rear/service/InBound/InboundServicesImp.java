package com.bsqx.bsqx_rear.service.InBound;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;

import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/16 16:24
 * @Author ： SuYan
 * @File ：InboundServicesImp.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
public interface InboundServicesImp {

    public Inbound addInBound(Inbound inbound);
    public Inbound deleteInBound(Long id);
    public Inbound editInBound(int id,Inbound inbound);
    public List<Inbound> searchInBound(String keyWord);

}
