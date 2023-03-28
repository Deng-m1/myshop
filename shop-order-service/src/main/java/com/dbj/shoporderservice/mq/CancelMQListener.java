package com.dbj.shoporderservice.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

/**
 * 取消订单
 *
 * @author admin
 * @date 2023/03/28
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${}",consumerGroup = "{mq.order.consumer.name}",
messageModel = MessageModel.BROADCASTING)
public class CancelMQListener {

}
