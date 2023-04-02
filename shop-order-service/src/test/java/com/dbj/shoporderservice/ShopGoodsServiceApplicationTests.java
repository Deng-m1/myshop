package com.dbj.shoporderservice;

import com.dbj.shopcommon.api.IOrderService;
import com.dbj.shopcommon.pojo.TradeOrder;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest(classes = ShopOrderServiceApplication.class)
class ShopGoodsServiceApplicationTests {
    @Autowired
    IOrderService orderService;


    @Test
    void contextLoads() {
        orderService.testDubbo();
    }
    @Test
    public void confirmOrder() throws IOException {

        Long coupouId = 4L;
        Long goodsId = 1L;
        Long userId = 2L;

        TradeOrder order = new TradeOrder();
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setCouponId(coupouId);
        order.setAddress("北京");
        order.setGoodsNumber(1);
        order.setGoodsPrice(new BigDecimal(8999));
        order.setShippingFee(BigDecimal.ZERO);
        order.setOrderAmount(new BigDecimal(8999));
        order.setMoneyPaid(new BigDecimal(100));
        orderService.confirmOrder(order);

        System.in.read();

    }

}
