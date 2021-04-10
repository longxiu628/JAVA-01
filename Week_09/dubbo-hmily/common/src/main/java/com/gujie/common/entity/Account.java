package com.gujie.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class Account implements Serializable {

    private long user_id;

    private BigDecimal usd;

    private BigDecimal cny;

}
