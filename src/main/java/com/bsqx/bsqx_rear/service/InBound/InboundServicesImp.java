package com.bsqx.bsqx_rear.service.InBound;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;

import java.util.List;

public interface InboundServicesImp {
    Inbound addInBound(Inbound inbound);
    Inbound deleteInBound(Integer id);
    Inbound editInBound(int id, Inbound inbound);
    List<Inbound> searchInBound(String keyWord);
    List<Inbound> allInbound();
}
