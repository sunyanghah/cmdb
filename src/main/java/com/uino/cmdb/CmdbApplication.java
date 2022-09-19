package com.uino.cmdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author dell
 * @Description 启动类
 * @Date 2021/09/01 11:32
 * @description
 */
@SpringBootApplication(scanBasePackages = {"com.uino.cmdb"})
@EnableConfigurationProperties
public class CmdbApplication {

    public static void main(String[] args) {

        SpringApplication.run(CmdbApplication.class, args);
    }

}
