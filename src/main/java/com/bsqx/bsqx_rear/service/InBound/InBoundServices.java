package com.bsqx.bsqx_rear.service.InBound;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;
import com.bsqx.bsqx_rear.repository.InBound.InboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class InBoundServices implements InboundServicesImp {

    private final InboundRepository ir;

    @Autowired
    public InBoundServices(InboundRepository ir) {
        this.ir = ir;
    }

    @Transactional
    @Override
    public Inbound addInBound(Inbound inbound) {
        String itemId = inbound.getItemId();
        Inbound findInbound = ir.findByItemId(itemId);
        if (findInbound != null) {
            return null;
        }
        return ir.save(inbound);
    }

    @Transactional
    @Override
    public Inbound deleteInBound(Integer id) {
        Optional<Inbound> byItemId = ir.findById(id);
        if (byItemId.isPresent()) {
            ir.deleteById(id);
            return byItemId.get();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public Inbound editInBound(int id, Inbound inbound) {
        Optional<Inbound> optionalInbound = ir.findById(id);
        if (optionalInbound.isPresent()) {
            Inbound existingInbound = optionalInbound.get();
            existingInbound.setItemId(inbound.getItemId());
            existingInbound.setItemName(inbound.getItemName());
            existingInbound.setBrand(inbound.getBrand());
            existingInbound.setQuantity(inbound.getQuantity());
            existingInbound.setInboundDate(inbound.getInboundDate());
            return ir.save(existingInbound);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Inbound> searchInBound(String keyWord) {
        try {
            // 尝试将关键词解析为整数（ID）
            int id = Integer.parseInt(keyWord);
            // 根据ID查询
            return ir.searchInboundsByKeywordOrId(null, null, null, id);
        } catch (NumberFormatException e) {
            try {
                // 尝试将关键词解析为日期
                Date date = parseDate(keyWord);
                // 设置日期的结束时间为当天的最后一秒钟
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                java.sql.Date endDate = new java.sql.Date(calendar.getTimeInMillis());
                // 根据日期查询
                return ir.searchInboundsByKeywordOrId(null, date, endDate, 0);
            } catch (ParseException ex) {
                // 解析日期失败
            } catch (Exception ex) {
                System.out.println("其他异常: " + ex.getMessage());
            }
        }
        // 如果都转换失败就查询关键词
        return ir.searchInboundsByKeywordOrId(keyWord, null, null, 0);
    }




    @Override
    public List<Inbound> allInbound() {
        return ir.findAll();
    }

    private Date parseDate(String keyword) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(keyword);
        return new Date(utilDate.getTime());
    }
}
