package com.dbj.shoporderweb;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.dbj.shopcommon.api.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableDubboConfiguration
class ShopOrderWebApplicationTests {
    @Reference
    IOrderService orderService;

    @Test
    void contextLoads() {
        System.out.println(orderService.testDubbo());
    }

}
