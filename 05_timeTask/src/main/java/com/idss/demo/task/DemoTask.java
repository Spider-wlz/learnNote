package com.idss.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 同步调用
 * @author wlz
 * @date 2022/10/10 15:11
 */
@Component
@Slf4j
public class DemoTask {

    public void taskOne() throws Exception {
        log.info("===执行任务1===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务1执行结束,总耗时={} 毫秒", end - start);
    }

    public void taskTwo() throws Exception {
        log.info("===执行任务2===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务2执行结束,总耗时={} 毫秒", end - start);
    }

    public void taskThere() throws Exception {
        log.info("===执行任务3===");
        long start = System.currentTimeMillis();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("任务3执行结束,总耗时={} 毫秒", end - start);
    }
}