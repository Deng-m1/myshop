package com.dbj.shoppayweb;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class ShopPayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopPayWebApplication.class, args);
    }

}
