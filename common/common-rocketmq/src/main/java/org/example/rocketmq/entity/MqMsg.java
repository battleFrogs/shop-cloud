package org.example.rocketmq.entity;

import lombok.Data;

@Data
public class MqMsg {


    private String topic;
    private String tag;
    private String content;

}
