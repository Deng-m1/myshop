package com.dbj.shoporderweb;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubboConfiguration
public class ShopOrderWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopOrderWebApplication.class, args);
    }

}
