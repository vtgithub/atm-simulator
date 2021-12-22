package com.egs.eval.bankclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BankClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankClientApplication.class, args);
    }

}
