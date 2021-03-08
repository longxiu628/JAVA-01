package com.hf.mybootDbJdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hfzhang
 * @date 2021/3/3
 */
@Data
@AllArgsConstructor
public class OrderInfo {
    private Long orderId;
    private Integer orderStatus;
    private Integer totalAmount;
}
