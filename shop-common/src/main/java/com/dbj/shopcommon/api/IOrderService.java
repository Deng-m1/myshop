package com.dbj.shopcommon.api;


import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.pojo.TradeOrder;

/**
 * @author dbj
 * @create 2019/12/27/9:52
 */
public interface IOrderService {
    /**
     * 确认订单
     * @param order
     * @return Result
     */
    Result confirmOrder(TradeOrder order);

    String testDubbo();
}
