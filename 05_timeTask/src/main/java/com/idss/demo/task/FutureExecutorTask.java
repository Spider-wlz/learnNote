package com.idss.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author wlz
 * @date 2022/10/10 14:53
 */
@Component
@Slf4j
public class FutureExecutorTask {

    @Async("newAsyncExecutor")
    public Future<String> taskOne() throws Exception {
        log.info("任务1线程名称 = {}", Thread.currentThread().getName());
        return new AsyncResult<>("1完成");
    }
    @Async("newAsyncExecutor")
    public Future<String> taskTwo() throws Exception {
        log.info("任务2线程名称 = {}", Thread.currentThread().getName());
        return new AsyncResult<>("2完成");
    }

    @Async
    public Future<String> taskThere() throws Exception {
        log.info("任务3线程名称 = {}", Thread.currentThread().getName());
        return new AsyncResult<>("执行任务3完成");
    }
}