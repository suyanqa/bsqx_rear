package com.bsqx.bsqx_rear.repository.InBound;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/16 16:30
 * @Author ： SuYan
 * @File ：InboundRepository.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@Repository
public interface InboundRepository extends JpaRepository<Inbound, Integer> {
    public Inbound findByItemId(Long id);
    void deleteById(Long id);
    @Query("SELECT i FROM Inbound i WHERE i.itemName LIKE %:keyword% OR i.brand LIKE %:keyword% OR i.inboundDate BETWEEN :startDate AND :endDate OR i.id = :id")
    List<Inbound> searchInboundsByKeywordOrId(String keyword, java.sql.Date startDate, java.sql.Date endDate, int id);

}

