package org.example.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"org.example.goods.mapper"})
@SpringBootApplication
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class);
    }

}
