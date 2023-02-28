package com.idss.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author wlz
 * @date 2022/10/10 11:28
 */
@Component
@Slf4j
public class AsyncTask {

    @Async
    public void taskAsyncOne() throws Exception{
        log.info("===执行任务1===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务1执行结束，总耗时={} 毫秒",end - start);
    }

    @Async
    public void taskAsyncTwo() throws Exception {
        log.info("===执行任务2===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务2执行结束,总耗时={} 毫秒", end - start);
    }

    @Async
    public void taskAsyncThere() throws Exception {
        log.info("===执行任务3===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务3执行结束,总耗时={} 毫秒", end - start);
    }
}