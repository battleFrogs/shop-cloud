package org.example.goods.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        System.out.println("GoodsTest");
        return "GoodsTest";
    }


}
