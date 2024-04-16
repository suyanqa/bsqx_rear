package com.bsqx.bsqx_rear.service.InBound;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;
import com.bsqx.bsqx_rear.repository.InBound.InboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


/**
 * # -*- coding: utf-8 -*-
 *
 * @Time ： 2024/4/16 16:24
 * @Author ： SuYan
 * @File ：InBoundServices.java
 * @email: suyan1254088@gmail.com
 * @IDE ：IntelliJ IDEA 2021.2.2
 * @Motto ：ABC(Always Be Coding)
 */
@Service
public class InBoundServices implements InboundServicesImp{

    private final InboundRepository ir;

    @Autowired
    public InBoundServices(InboundRepository ir) {
        this.ir = ir;
    }


    @Transactional
    @Override
    public Inbound addInBound(Inbound inbound) {
        int itemId = inbound.getItemId();
        // 向下转型
        Inbound byItemId = ir.findByItemId((long) itemId);

        if (byItemId != null) {
            return null;
        }else {
            return ir.save(inbound);
        }

    }

    @Transactional
    @Override
    public Inbound deleteInBound(Long id) {
        Optional<Inbound> byItemId = Optional.ofNullable(ir.findByItemId(id));
        if (byItemId.isPresent()) {
            ir.deleteById(id);
            return byItemId.get();
        }else {
            return null;
        }
    }

    /**
     * 根据给定的ID编辑数据库中的Inbound对象。
     * 如果数据库中存在具有给定ID的记录，则更新该记录的信息并返回更新后的对象，否则返回null。
     *
     * @param id 要编辑的Inbound对象的ID
     * @param inbound 包含要更新信息的Inbound对象
     * @return 如果成功找到并编辑了Inbound对象，则返回更新后的对象；否则返回null
     */
    @Transactional
    @Override
    public Inbound editInBound(int id, Inbound inbound) {
        /** 查询数据库中是否具有该ID,悠久发挥InBound对象,没有就返回null
         *  这里会调用InBoundRepository的方法findById进行查询
         */
        Optional<Inbound> optionalInbound = ir.findById(id);
        if (optionalInbound.isPresent()) {
            Inbound existingInbound = optionalInbound.get();
            // 更新数据库中的信息
            existingInbound.setItemId(inbound.getItemId());
            existingInbound.setItemName(inbound.getItemName());
            existingInbound.setBrand(inbound.getBrand());
            existingInbound.setQuantity(inbound.getQuantity());
            existingInbound.setInboundDate(inbound.getInboundDate());

            // 保存更新后的对象并返回
            return ir.save(existingInbound);
        } else {
            return null;
        }
    }

//    @Transactional
//    @Override
//    public List<Inbound> searchInBound(String keyWord){
//        try {
//            // 尝试吧关键词解析为int
//            int id = Integer.parseInt(keyWord);
//            System.out.println("/inbound/search接收到的id: "+id);
//        } catch (NumberFormatException e) {
//            try {
//                // 尝试转换为日期格式
//                Date date = parseDate(keyWord);
//                System.out.println("/inbound/search接收到的date: "+date);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(date);
//                calendar.set(Calendar.HOUR_OF_DAY,23);
//                calendar.set(Calendar.MINUTE,59);
//                calendar.set(Calendar.SECOND,59);
//                java.sql.Date endDate = new Date(date.getTime());
//            }catch (ParseException ex) {
//
//            }catch (Exception ex) {
//                System.out.println("其他异常: "+ex.getMessage());
//            }
//        }
//        System.out.println("/inbound/search接收到的keyword: "+keyWord);
//        // 如果都转换失败就查询关键词
//        return ir.searchInboundsByKeywordOrId(keyWord,null,null,0);
//    }

    @Transactional
    @Override
    public List<Inbound> searchInBound(String keyWord){
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
                calendar.set(Calendar.HOUR_OF_DAY,23);
                calendar.set(Calendar.MINUTE,59);
                calendar.set(Calendar.SECOND,59);
                java.sql.Date endDate = new Date(calendar.getTimeInMillis());
                // 根据日期查询
                return ir.searchInboundsByKeywordOrId(null, date, endDate, 0);
            } catch (ParseException ex) {
                // 解析日期失败
            } catch (Exception ex) {
                System.out.println("其他异常: "+ex.getMessage());
            }
        }
        // 如果都转换失败就查询关键词
        return ir.searchInboundsByKeywordOrId(keyWord, null, null, 0);
    }



    // 解析日期字符串的方法
    private Date parseDate(String keyword) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(keyword);
        return new Date(utilDate.getTime());
    }

}
