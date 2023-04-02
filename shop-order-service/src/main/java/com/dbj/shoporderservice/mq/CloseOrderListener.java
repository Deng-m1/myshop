package com.dbj.shoporderservice.mq;

import com.dbj.shopcommon.constant.ShopCode;
import com.dbj.shopcommon.entity.MQEntity;
import com.dbj.shopcommon.pojo.TradeOrder;
import com.dbj.shoporderservice.mapper.TradeOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@RocketMQMessageListener(consumerGroup = "delay", topic = "delay")
@Component
public class CloseOrderListener implements RocketMQListener<MessageExt> {
    @Autowired
    TradeOrderMapper tradeOrderMapper;
    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("订单超时，即将关闭");
        try {
            byte[] body = messageExt.getBody();
            String message = new String(body, StandardCharsets.UTF_8);
            MQEntity mqEntity = new MQEntity();
            BeanUtils.copyProperties(message, mqEntity);

            Long orderId = mqEntity.getOrderId();
            TradeOrder tradeOrder = tradeOrderMapper.selectByPrimaryKey(orderId);
            if (tradeOrder.getOrderStatus().equals(ShopCode.SHOP_ORDER_PAY_STATUS_NO_PAY.getCode())){
                tradeOrder.setOrderStatus(ShopCode.SHOP_ORDER_CANCEL.getCode());
                tradeOrderMapper.insert(tradeOrder);
            }
            log.info(orderId+"订单已取消");

        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
