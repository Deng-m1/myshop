package com.dbj.shopcommon.api;


import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.pojo.TradeUser;
import com.dbj.shopcommon.pojo.TradeUserMoneyLog;

/**
 * @author dbj
 * @create 2019/12/27/10:04
 */
public interface IUserService {
    TradeUser findOne(Long userId);

    Result updateMoneyPaid(TradeUserMoneyLog userMoneyLog);
}
