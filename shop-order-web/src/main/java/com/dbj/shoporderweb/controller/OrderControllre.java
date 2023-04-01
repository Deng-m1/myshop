package com.dbj.shoporderweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dbj.shopcommon.api.IOrderService;
import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.pojo.TradeOrder;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wulang
 * @create 2019/12/27/16:58
 */
@RestController
@RequestMapping("/order")
public class OrderControllre {

    @Reference
    private IOrderService orderService;

    @RequestMapping("/confirm")
    public Result confirmOrder(@RequestBody TradeOrder order){
        return orderService.confirmOrder(order);
    }

    @RequestMapping("/ni")
    public Result text(){
        String s=orderService.testDubbo();
        System.out.println(s);
        return new Result();
    }

}
