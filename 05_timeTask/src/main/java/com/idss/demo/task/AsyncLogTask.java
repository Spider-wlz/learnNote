package com.idss.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author wlz
 * @date 2022/10/11 10:27
 */

@Slf4j
@Component
public class AsyncLogTask {

    @Async("logExecutor")
    public void recordLoginLog(Integer userId) {
        log.info("开始处理 登陆日志id = {}", userId);

        // TODO: 假设业务处理

        log.info("处理结束 登陆日志id = {}", userId);
    }
}