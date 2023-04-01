package com.dbj.shoporderservice;

import com.dbj.shopcommon.api.IOrderService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopGoodsServiceApplicationTests {
    @Reference
    IOrderService orderService;


    @Test
    void contextLoads() {
        orderService.testDubbo();
    }

}
