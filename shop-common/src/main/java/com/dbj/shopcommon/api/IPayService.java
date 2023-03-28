package com.dbj.shopcommon.api;

import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.pojo.TradePay;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 支付订单接口
 *
 * @author dbj
 * @create 2019/12/27/15:52
 */
public interface IPayService {

    public Result createPayment(TradePay tradePay);

    public Result callbackPayment(TradePay tradePay) throws InterruptedException, RemotingException, MQClientException, MQBrokerException;

}
