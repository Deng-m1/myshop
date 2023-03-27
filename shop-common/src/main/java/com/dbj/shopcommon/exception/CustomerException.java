package com.dbj.shopcommon.exception;

import com.dbj.shopcommon.constant.ShopCode;

/**
 * 自定义异常
 */
public class CustomerException extends RuntimeException{

    private ShopCode shopCode;

    public CustomerException(ShopCode shopCode) {
        this.shopCode = shopCode;
    }
}
