package com.dbj.shopcouponservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dbj.shopcommon.api.ICouponService;
import com.dbj.shopcommon.constant.ShopCode;
import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.exception.CastException;

import com.dbj.shopcommon.pojo.TradeCoupon;
import com.dbj.shopcouponservice.mapper.TradeCouponMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 优惠卷Service
 *
 * @author dbj.shopcommon
 * @create 2019/12/27/11:18
 */
@Component
@DubboService(interfaceClass = ICouponService.class)
public class CouponServiceImpl implements ICouponService{

    @Autowired
    private TradeCouponMapper couponMapper;

    @Override
    public TradeCoupon findOne(Long coupouId) {
        if(coupouId==null){
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        return couponMapper.selectByPrimaryKey(coupouId);
    }

    @Override
    public Result updateCouponStatus(TradeCoupon coupon) {
        if(coupon==null||coupon.getCouponId()==null){
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        //更新优惠券状态
        couponMapper.updateByPrimaryKey(coupon);
        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(),ShopCode.SHOP_SUCCESS.getMessage());
    }
}

