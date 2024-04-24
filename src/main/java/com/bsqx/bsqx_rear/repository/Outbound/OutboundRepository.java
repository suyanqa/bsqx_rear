package com.bsqx.bsqx_rear.repository.Outbound;

import com.bsqx.bsqx_rear.DTO.Outbound.Outbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/14 15:47
 * @Author ： SuYan
 * @File ：OutboundRepository.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */

@Repository
public interface OutboundRepository extends JpaRepository<Outbound, Integer> {

    @Query("SELECT o FROM outbound o WHERE o.itemName LIKE %:keyword% OR o.brand LIKE %:keyword% OR o.outboundDate BETWEEN :startDate AND :endDate OR o.id = :id")
    List<Outbound> searchOutboundsByKeywordOrId(String keyword, Date startDate, Date endDate, int id);
    Outbound findByItemId(String itemId);
}