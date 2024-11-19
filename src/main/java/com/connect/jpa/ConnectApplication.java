package com.connect.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnectApplication.class, args);
    }
}

@RestController
class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bem-vindo ao Connect Azul!";
    }
}
