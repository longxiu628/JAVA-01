package com.hf.mybootDbDruid.rest;

import com.hf.mybootDbDruid.entity.OrderInfo;
import com.hf.mybootDbDruid.service.OrderInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hfzhang
 * @date 2021/3/3
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation(value = "批量添加订单",notes = "批量添加订单")
    @RequestMapping(value = "/addOrderInfo", method = RequestMethod.POST)
    public int[] addBatchOrderInfo(@RequestBody OrderInfo orderInfo){
        try {
            List<OrderInfo> orderInfoList = new ArrayList<>();
            for(int i=0;i<1000000;i++){
                orderInfoList.add(orderInfo);
            }
            orderInfoService.saveBatchOrderInfo(orderInfoList);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
