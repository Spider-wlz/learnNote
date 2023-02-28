package com.idss.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author wlz
 * @date 2022/10/10 11:58
 */
@Component
@Slf4j
public class FutureTask {

    @Async
    public Future<String> taskAsyncOne() throws Exception{
        log.info("===执行任务1===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务1执行结束，总耗时={} 毫秒",end - start);
        return new AsyncResult<>("1完成");
    }

    @Async
    public Future<String> taskAsyncTwo() throws Exception {
        log.info("===执行任务2===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务2执行结束,总耗时={} 毫秒", end - start);
        return new AsyncResult<>("2完成");
    }

    @Async
    public Future<String> taskAsyncThere() throws Exception {
        log.info("===执行任务3===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务3执行结束,总耗时={} 毫秒", end - start);
        return new AsyncResult<>("3完成");
    }
}