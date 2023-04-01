package com.dbj.shoporderservice.mq;

import com.alibaba.fastjson.JSON;
import com.dbj.shopcommon.constant.ShopCode;
import com.dbj.shopcommon.entity.MQEntity;
import com.dbj.shopcommon.pojo.TradeOrder;
import com.dbj.shoporderservice.mapper.TradeOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 取消订单
 *
 * @author admin
 * @date 2023/03/28
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.order.topic}",consumerGroup = "${mq.order.consumer.group.name}",messageModel = MessageModel.BROADCASTING )
public class CancelMQListener implements RocketMQListener<MessageExt> {
    @Autowired
    TradeOrderMapper tradeOrderMapper;


    @Override
    public void onMessage(MessageExt messageExt) {
        try{
            String body=new String(messageExt.getBody(), StandardCharsets.UTF_8);
            MQEntity mqEntity= JSON.parseObject(body,MQEntity.class);
            log.info("orderservice接受消息成功");
            //2.查询订单
            TradeOrder tradeOrder = tradeOrderMapper.selectByPrimaryKey(mqEntity.getOrderId());
            //3.更新订单状态为取消
            tradeOrder.setOrderStatus(ShopCode.SHOP_ORDER_CANCEL.getCode());
            tradeOrderMapper.updateByPrimaryKey(tradeOrder);
            log.info("状态订单设置为取消");

        }catch (Exception e)
        {
            e.printStackTrace();
            log.info("订单取消失败");
        }
    }
}
