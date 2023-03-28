package com.dbj.shoporderservice.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dbj.shopcommon.api.ICouponService;
import com.dbj.shopcommon.api.IGoodsService;
import com.dbj.shopcommon.api.IOrderService;
import com.dbj.shopcommon.api.IUserService;
import com.dbj.shopcommon.constant.ShopCode;
import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.exception.CastException;
import com.dbj.shopcommon.pojo.TradeCoupon;
import com.dbj.shopcommon.pojo.TradeGoods;
import com.dbj.shopcommon.pojo.TradeOrder;
import com.dbj.shopcommon.pojo.TradeUser;
import com.dbj.shopcommon.utils.IDWorker;
import com.dbj.shoporderservice.mapper.TradeOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
public class OrderServiceimpl implements IOrderService {
    @Reference
    private IGoodsService goodsService;
    @Reference
    private IUserService userService;
    @Reference
    private ICouponService couponService;
    @Value("${mq.order.topic}")
    private String topic;

    @Value("${mq.order.tag.cancel}")
    private String tag;

    @Autowired
    private TradeOrderMapper orderMapper;

    @Autowired
    private IDWorker idWorker;
    @Override
    public Result confirmOrder(TradeOrder order) {
        checkOrder(order);
        Long orderId=savePreOrder(order);
        return null;
    }

    /**
     * 生成预订单
     *
     * @param order 订单
     * @return {@link Long}
     */
    private Long savePreOrder(TradeOrder order) {
        //1. 设置订单状态为不可见
        order.setOrderStatus(ShopCode.SHOP_ORDER_NO_CONFIRM.getCode());
        //2. 设置订单ID
        long orderId = idWorker.nextId();
        order.setOrderId(orderId);
        //3. 核算订单运费
        BigDecimal shippingFee = calculateShippingFee(order.getOrderAmount());
        if(order.getShippingFee().compareTo(shippingFee)!=0){
            CastException.cast(ShopCode.SHOP_ORDER_SHIPPINGFEE_INVALID);
        }
        //4. 核算订单总金额是否合法
        BigDecimal orderAmount = order.getGoodsPrice().multiply(new BigDecimal(order.getGoodsNumber()));
        orderAmount.add(shippingFee);
        if(order.getOrderAmount().compareTo(orderAmount)!=0){
            CastException.cast(ShopCode.SHOP_ORDERAMOUNT_INVALID);
        }
        //5.判断用户是否使用余额
        BigDecimal moneyPaid = order.getMoneyPaid();
        if(moneyPaid!=null){
            //5.1 订单中余额是否合法
            int r = moneyPaid.compareTo(BigDecimal.ZERO);

            //余额小于0
            if(r==-1){
                CastException.cast(ShopCode.SHOP_MONEY_PAID_LESS_ZERO);
            }

            //余额大于0
            if(r==1){
                TradeUser user = userService.findOne(order.getUserId());

                if(moneyPaid.compareTo(new BigDecimal(user.getUserMoney()))==1){
                    CastException.cast(ShopCode.SHOP_MONEY_PAID_INVALID);
                }
            }

        }else{
            order.setMoneyPaid(BigDecimal.ZERO);
        }
        //6.判断用户是否使用优惠券
        Long couponId = order.getCouponId();
        if(couponId!=null){
            TradeCoupon coupon = couponService.findOne(couponId);
            //6.1 判断优惠券是否存在
            if(coupon==null){
                CastException.cast(ShopCode.SHOP_COUPON_NO_EXIST);
            }
            //6.2 判断优惠券是否已经被使用
            if(coupon.getIsUsed().intValue()==ShopCode.SHOP_COUPON_ISUSED.getCode().intValue()){
                CastException.cast(ShopCode.SHOP_COUPON_ISUSED);
            }

            order.setCouponPaid(coupon.getCouponPrice());

        }else{
            order.setCouponPaid(BigDecimal.ZERO);
        }
        //7.核算订单支付金额    订单总金额-余额-优惠券金额
        BigDecimal payAmount = order.getOrderAmount().subtract(order.getMoneyPaid()).subtract(order.getCouponPaid());
        order.setPayAmount(payAmount);
        //8.设置下单时间
        order.setAddTime(new Date());
        //9.保存订单到数据库
        orderMapper.insert(order);
        //10.返回订单ID
        return orderId;
    }

    /**
     * 计算运费
     *
     * @param orderAmount 订单数量
     * @return {@link BigDecimal}
     */
    private BigDecimal calculateShippingFee(BigDecimal orderAmount)
    {
        if (orderAmount.compareTo(BigDecimal.valueOf(100))==1)
        {
            return BigDecimal.ZERO;
        }
        else {
            return new BigDecimal(12);
        }
    }
    /**
     * 校验订单
     *
     * @param order
     */
    private void checkOrder(TradeOrder order) {
        //to check the order is exist
        if (order==null)
        {
            CastException.cast(ShopCode.SHOP_COUPON_INVALIED);
        }
        //to check the product of the order is exist
        TradeGoods goods = goodsService.findOne(order.getGoodsId());
        if (goods==null)
        {
            CastException.cast(ShopCode.SHOP_GOODS_NO_EXIST);
        }
        //3.校验下单用户是否存在
        TradeUser user = userService.findOne(order.getUserId());
        if (user == null) {
            CastException.cast(ShopCode.SHOP_USER_NO_EXIST);
        }
        //4.校验商品单价是否合法
        if (order.getGoodsPrice().compareTo(goods.getGoodsPrice()) != 0) {
            CastException.cast(ShopCode.SHOP_GOODS_PRICE_INVALID);
        }
        //5.校验订单商品数量是否合法
        if (order.getGoodsNumber() >= goods.getGoodsNumber()) {
            CastException.cast(ShopCode.SHOP_GOODS_NUM_NOT_ENOUGH);
        }

        log.info("校验订单通过" + order.getOrderId() + "," + order.getUserId());

    }
}
