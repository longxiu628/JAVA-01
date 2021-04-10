package com.gujie.common.service;

import com.gujie.common.entity.Account;
import org.dromara.hmily.annotation.Hmily;

import java.math.BigDecimal;

public interface TransMoneyService {

    Account findAccountByUserId(long userId);

    @Hmily
    Boolean transMoney(long userId, Account trans);

}
