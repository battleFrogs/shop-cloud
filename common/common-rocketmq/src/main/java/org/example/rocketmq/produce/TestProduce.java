package org.example.rocketmq.produce;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.rocketmq.entity.MqMsg;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestProduce {

    @Resource
    private RocketMQTemplate rocketMqTemplate;


    public void send(MqMsg msg) {
        rocketMqTemplate.send(msg.getTopic() + ":" + msg.getTag(), MessageBuilder.withPayload(msg.getContent()).build());
    }

}
