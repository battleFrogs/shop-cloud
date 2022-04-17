package org.example.visual;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAdminServer
@SpringBootApplication
public class VisualApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisualApplication.class);
    }


}
