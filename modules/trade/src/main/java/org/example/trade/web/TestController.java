package org.example.trade.web;

import org.example.rocketmq.entity.MqMsg;
import org.example.rocketmq.produce.TestProduce;
import org.example.trade.entity.Student;
import org.example.trade.repository.StudentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private TestProduce testProduce;
    @Resource
    private StudentRepository studentRepository;

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


    @RequestMapping("/test2")
    public void test2() {
        Student student = new Student();
        student.setName("sss");
        student.setAge(123);

        studentRepository.save(student);
    }

}
