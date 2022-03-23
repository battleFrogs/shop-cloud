package org.example.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.example.system.dao.mapper")
@SpringBootApplication
public class SystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }

}
