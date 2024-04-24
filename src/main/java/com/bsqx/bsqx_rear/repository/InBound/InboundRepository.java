package com.bsqx.bsqx_rear.repository.InBound;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboundRepository extends JpaRepository<Inbound, Integer> {
    Inbound findByItemId(String id);

    void deleteById(Integer id);

    @Query("SELECT i FROM Inbound i WHERE i.itemName LIKE %:keyword% OR i.brand LIKE %:keyword% OR i.inboundDate BETWEEN :startDate AND :endDate OR i.id = :id")
    List<Inbound> searchInboundsByKeywordOrId(String keyword, java.sql.Date startDate, java.sql.Date endDate, int id);
}
