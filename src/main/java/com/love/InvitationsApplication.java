package com.love;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author admin
 */
@MapperScan(value = "com.love.mapper")
@SpringBootApplication
public class InvitationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvitationsApplication.class, args);
    }
}
