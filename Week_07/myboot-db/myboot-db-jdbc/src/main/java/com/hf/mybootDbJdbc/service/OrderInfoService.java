package com.hf.mybootDbJdbc.service;

import com.hf.mybootDbJdbc.entity.OrderInfo;

import java.util.List;

/**
 * Create by hfzhang
 *
 * @date 2021/3/3
 */
public interface OrderInfoService {

    /**
     * 添加订单
     * @param orderInfo
     * @return
     */
    int addOrderInfo(OrderInfo orderInfo);

    /**
     * 批量添加订单信息
     * @param orderInfoList
     * @return
     */
    int[] addBatchOrderInfo(List<OrderInfo> orderInfoList);
}
