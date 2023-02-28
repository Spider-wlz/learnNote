package com.idss.demo;

import com.idss.demo.task.AsyncTask;
import com.idss.demo.task.FutureExecutorTask;
import com.idss.demo.task.FutureTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.StringJoiner;
import java.util.concurrent.Future;

/**
 * @author wlz
 * @date 2022/10/10 11:40
 */
@EnableAsync
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AsyncTest {

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private FutureTask futureTask;

    @Autowired
    private FutureExecutorTask executorTask;

    @Test
    public void runAsync() throws Exception {
        long start = System.currentTimeMillis();
        asyncTask.taskAsyncOne();
        asyncTask.taskAsyncTwo();
        asyncTask.taskAsyncThere();
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        log.info("总任务执行结束,总耗时={} 毫秒", end - start);
    }

    /**
     * 异步回调
     * @throws Exception
     */
    @Test
    public void runAsyncFuture() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> taskOne = futureTask.taskAsyncOne();
        Future<String> taskTwo = futureTask.taskAsyncTwo();
        Future<String> taskThere = futureTask.taskAsyncThere();
        while (true) {
            if (taskOne.isDone() && taskTwo.isDone() && taskThere.isDone()) {
                log.info("任务1返回结果={},任务2返回结果={},任务3返回结果={},", taskOne.get(), taskTwo.get(), taskThere.get());
                break;
            }
        }
        long end = System.currentTimeMillis();
        log.info("总任务执行结束,总耗时={} 毫秒", end - start);
    }

    /**
     * 自定义线程池，不使用默认SimpleAsyncTaskExecutor线程池
     * @throws Exception
     */
    @Test
    public void runAsyncExecutor() throws Exception {
        long start = System.currentTimeMillis();
        executorTask.taskOne();
        executorTask.taskTwo();
        executorTask.taskThere();

        long end = System.currentTimeMillis();
        log.info("总任务执行结束,总耗时={} 毫秒", end - start);
    }

}