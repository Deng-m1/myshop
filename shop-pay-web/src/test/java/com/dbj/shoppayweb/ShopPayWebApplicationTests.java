package com.dbj.shoppayweb;

import com.dbj.shopcommon.constant.ShopCode;
import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.pojo.TradePay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@SpringBootTest
class ShopPayWebApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${shop.pay.baseURI}")
    private String baseURI;

    @Value("${shop.pay.createPayment}")
    private String createPaymentPath;

    @Value("${shop.pay.callbackPayment}")
    private String callBackPaymentPath;

    @Test
    public void createPayment(){
        long orderId = 352537369385242624L;
        TradePay tradePay = new TradePay();
        tradePay.setOrderId(orderId);
        tradePay.setPayAmount(new BigDecimal(880));

        Result result = restTemplate.postForEntity(baseURI + createPaymentPath, tradePay, Result.class).getBody();
        System.out.println(result);
    }

    @Test
    public void callBackPayment(){
        //payId要根据自己数据库来看
        long payId = 839943432024760320L;
        long orderId = 352537369385242624L;

        TradePay tradePay = new TradePay();
        tradePay.setPayId(payId);
        tradePay.setOrderId(orderId);
        tradePay.setIsPaid(ShopCode.SHOP_ORDER_PAY_STATUS_IS_PAY.getCode());
        Result result = restTemplate.postForEntity(baseURI + callBackPaymentPath, tradePay, Result.class).getBody();
        System.out.println(result);
    }

}
