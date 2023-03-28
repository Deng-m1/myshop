package com.dbj.shopcommon.api;

import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.pojo.TradeGoods;
import com.dbj.shopcommon.pojo.TradeGoodsNumberLog;

/**
 * @author dbj
 * @create 2019/12/27/10:00
 */
public interface IGoodsService {
    /**
     * 根据ID查询商品对象
     * @param goodsId
     * @return
     */
    TradeGoods findOne(Long goodsId);

    /**
     * 扣减库存
     * @param goodsNumberLog
     * @return
     */
    Result reduceGoodsNum(TradeGoodsNumberLog goodsNumberLog);
}
