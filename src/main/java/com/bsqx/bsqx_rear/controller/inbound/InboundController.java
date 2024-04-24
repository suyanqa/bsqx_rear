package com.bsqx.bsqx_rear.controller.inbound;

import com.bsqx.bsqx_rear.DTO.Inbound.Inbound;
import com.bsqx.bsqx_rear.response.ApiResponse;
import com.bsqx.bsqx_rear.service.InBound.InBoundServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inbound")
public class InboundController {
    private final InBoundServices is;

    public InboundController(InBoundServices is) {
        this.is = is;
    }

    @PostMapping("/add")
    public ApiResponse addInBound(@RequestBody Inbound ib) {
        String itemId = ib.getItemId();
        if (StringUtils.isEmpty(itemId)) {
            return new ApiResponse(false, "输入的ID不符合需求", ib);
        }

        Inbound inbound = is.addInBound(ib);

        if (inbound != null) {
            return new ApiResponse(true, "入库成功", inbound);
        } else {
            return new ApiResponse(false, "相同配件ID已存在", null);
        }
    }

    @PostMapping("/delete/{id}")
    public ApiResponse deleteInBOUND(@PathVariable String id){
        if (StringUtils.isEmpty(id)) {
            return new ApiResponse(false, "您输入的ID不符合规范", null);
        }

        Inbound inbound = is.deleteInBound(Integer.parseInt(id));

        if (inbound != null) {
            return new ApiResponse(true, "删除成功", inbound);
        } else {
            return new ApiResponse(false, "没有找到ID为 " + id + " 的记录", null);
        }
    }

    @PostMapping("/edit")
    public ApiResponse editInBound(@RequestBody Inbound ib) {
        int id = ib.getId();
        if (id < 0) {
            return new ApiResponse(false, "ID不符合规范", null);
        }

        Inbound inbound = is.editInBound(id, ib);
        if (inbound != null) {
            return new ApiResponse(true, "编辑成功", inbound);
        } else {
            return new ApiResponse(false, "编辑失败，不存在ID为 " + id + " 的记录", null);
        }
    }

    @PostMapping("/search")
    public ApiResponse<List<Inbound>> searchInBound(@RequestBody Map<String,String> requestBody){
        String keyword = requestBody.get("keyword");
        if (StringUtils.isEmpty(keyword)) {
            return new ApiResponse<>(false, "请输入有效的查询关键字", null);
        }

        List<Inbound> inbounds = is.searchInBound(keyword);
        if (!inbounds.isEmpty()){
            return new ApiResponse<>(true, "查询成功", inbounds);
        } else {
            return new ApiResponse<>(false, "搜索失败,请查看日志", null);
        }
    }

    @PostMapping("/all")
    public ApiResponse<List<Inbound>> allInbound(){
        return new ApiResponse<>(true, "请求成功", is.allInbound());
    }
}
