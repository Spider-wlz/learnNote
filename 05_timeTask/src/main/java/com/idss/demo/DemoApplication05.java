package com.idss.demo;

import com.idss.demo.entity.ThreadpoolProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wlz
 * @date 2022/10/9 16:42
 */

@EnableAsync
@EnableScheduling
@EnableConfigurationProperties(ThreadpoolProperties.class)
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class DemoApplication05 {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication05.class,args);
        System.out.println("(*^▽^*)启动成功!!!(〃'▽'〃)");
    }
}