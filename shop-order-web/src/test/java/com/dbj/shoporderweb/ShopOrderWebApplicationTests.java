package com.dbj.shoporderweb;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dbj.shopcommon.api.IGoodsService;
import com.dbj.shopcommon.api.IOrderService;
import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.pojo.TradeGoods;
import com.dbj.shopcommon.pojo.TradeOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopOrderWebApplication.class)
class ShopOrderWebApplicationTests {
    @Reference
    IOrderService orderService;

    @Reference
    IGoodsService goodsService;

    @Test
    void contextLoads() {
        System.out.println(orderService.testDubbo());
        TradeGoods one = goodsService.findOne(1L);
        System.out.println(one.getGoodsDesc());
    }

    @Autowired
    private RestTemplate restTemplate;

    @Value("${shop.order.baseURI}")
    private String baseURI;

    @Value("${shop.order.confirm}")
    private String confirmOrderPath;

    @Test
    public void confirmOrder(){
        Long coupouId = 345988230098857984L;
        Long goodsId = 345959443973935104L;
        Long userId = 345963634385633280L;

        TradeOrder order = new TradeOrder();
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setCouponId(coupouId);
        order.setAddress("北京");
        order.setGoodsNumber(1);
        order.setGoodsPrice(new BigDecimal(1000));
        order.setShippingFee(BigDecimal.ZERO);
        order.setOrderAmount(new BigDecimal(1000));
        order.setMoneyPaid(new BigDecimal(100));

        Result result = restTemplate.postForEntity(baseURI + confirmOrderPath, order, Result.class).getBody();

        System.out.println(result);
    }

}
