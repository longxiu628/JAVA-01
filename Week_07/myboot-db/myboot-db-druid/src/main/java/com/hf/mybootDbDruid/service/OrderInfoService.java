package com.hf.mybootDbDruid.service;


import com.hf.mybootDbDruid.entity.OrderInfo;

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

    void saveBatchOrderInfo(List<OrderInfo> orderInfoList);

    /**
     * 批量添加订单信息
     * @param orderInfoList
     * @return
     */
    int[] addBatchOrderInfo(List<OrderInfo> orderInfoList);
}
