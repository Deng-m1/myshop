package com.dbj.shoporderservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dbj.shopcommon.api.IGoodsService;
import com.dbj.shopcommon.pojo.TradeGoods;
import com.dbj.shopcommon.pojo.TradeOrder;
import com.dbj.shoporderservice.mapper.TradeOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopOrderServiceApplicationTests {
    @Autowired
    TradeOrderMapper tradeOrderMapper;
    /*@Reference
    IGoodsService goodsService;

    @Test
    void contextLoads() {
        TradeOrder tradeOrder = tradeOrderMapper.selectByPrimaryKey(new Long(1));
        System.out.println(tradeOrder.getUserId());
        TradeGoods one = goodsService.findOne(1L);
        System.out.println(one.getGoodsDesc());
    }
*/

}
