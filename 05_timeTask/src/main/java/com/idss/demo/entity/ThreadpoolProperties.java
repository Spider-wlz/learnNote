package com.idss.demo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wlz
 * @date 2022/10/11 10:26
 */

@Data
@ConfigurationProperties(prefix = ThreadpoolProperties.PREFIX)
public class ThreadpoolProperties {

    public static final String PREFIX = "threadpool";
    /**核心线程数*/
    private Integer corePoolSize;
    /**最大线程数*/
    private Integer maxPoolSize;
    /**队列存储数*/
    private Integer queueCapacity;
    /**最大线程存活时间*/
    private Integer keepAliveSeconds;
}