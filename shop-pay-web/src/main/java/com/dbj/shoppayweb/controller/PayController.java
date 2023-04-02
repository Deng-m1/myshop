package com.dbj.shoppayweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dbj.shopcommon.api.IPayService;
import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.pojo.TradePay;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dbj
 * @create 2019/12/27/17:02
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Reference
    private IPayService payService;

    @RequestMapping("/createPayment")
    public Result createPayment(@RequestBody TradePay pay){
        return payService.createPayment(pay);
    }

    @RequestMapping("/callBackPayment")
    public Result callBackPayment(@RequestBody TradePay pay) throws Exception {
        return payService.callbackPayment(pay);
    }

}
