package org.example.trade.web;

import org.example.rocketmq.entity.MqMsg;
import org.example.rocketmq.produce.TestProduce;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private TestProduce testProduce;

    @RequestMapping("/test")
    public String test() {
        System.out.println("TradeTest");
        return "TradeTest";
    }

    @RequestMapping("/test1")
    public void test1() {
        MqMsg mqMsg = new MqMsg();
        mqMsg.setTopic("testTopic");
        mqMsg.setTag("send");
        mqMsg.setContent("我来了");
        testProduce.send(mqMsg);
    }


}
