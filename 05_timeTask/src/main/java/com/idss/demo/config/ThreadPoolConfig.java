package com.idss.demo.config;

import com.idss.demo.entity.ThreadpoolProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wlz
 * @date 2022/10/11 10:27
 */

@Configuration
@EnableConfigurationProperties(value = ThreadpoolProperties.class)
public class ThreadPoolConfig {

    /**
     * 创建线程池
     */
    @Bean("logExecutor")
    public ThreadPoolTaskExecutor taskExecutor(ThreadpoolProperties properties) {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix("-----日志线程池-------");
        pool.setCorePoolSize(properties.getCorePoolSize());
        pool.setMaxPoolSize(properties.getMaxPoolSize());
        pool.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        pool.setQueueCapacity(properties.getQueueCapacity());
        // 指定拒绝策略
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        pool.initialize();
        return pool;
    }
}