package org.example.trade.consume;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RocketMQMessageListener(topic = "testTopic", consumerGroup = "testGroup", selectorExpression = "send")
public class TestConsume implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("消费者成功消费消息:{}", s);
    }


}
