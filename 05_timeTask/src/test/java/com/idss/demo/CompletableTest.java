package com.idss.demo;

import com.idss.demo.config.SmallTool;
import com.idss.demo.task.DemoTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.*;

/**
 * @author wlz
 * @date 2022/10/10 15:03
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CompletableTest {

    @Autowired
    private DemoTask demoTask;

    /**
     * 第一阶段：开启异步
     *
     * 小例子：小卓去参加自行车比赛，等待裁判员宣布开始过程中会摆好姿势准备起跑
     * 裁判需要单独的线程
     */
    @Test
    public void cyclingRaceAsync() {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        SmallTool.printTimeAndThread("小卓来到自行车赛道");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("裁判员就位");
            SmallTool.sleepMills(200);
            SmallTool.printTimeAndThread("裁判员打枪");
            SmallTool.sleepMills(100);
            return "开始了";
        },executorService);

        SmallTool.printTimeAndThread("小卓准备起跑");
        SmallTool.printTimeAndThread(String.format("%s,小卓比赛中",cf1.join()));
    }

    /**
     * 第二阶段：thenCompose 异步任务的连接
     *
     * 任务一完成才开始任务二
     * 裁判员就位后告诉打枪员，就可以打枪宣布开始了
     */
    @Test
    public void cyclingRaceAsync02() {
        SmallTool.printTimeAndThread("小卓来到自行车赛道");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("裁判员就位");
            SmallTool.sleepMills(200);
            return "裁判就位了";
        }).thenCompose(start -> CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("发令员打枪");
            SmallTool.sleepMills(100);
            return start + "枪响了";
        }));

        SmallTool.printTimeAndThread("小卓准备起跑");
        SmallTool.printTimeAndThread(String.format("%s,小卓比赛中",cf1.join()));
    }

    /**
     * 第三阶段：thenCombine And组合关系
     *
     * 当任务一和任务二都完成再执行任务三
     * 裁判员就位 打枪员就位是同时进行的 都完成才能打枪
     */
    @Test
    public void cyclingRaceAsync03() {
        SmallTool.printTimeAndThread("小卓来到自行车赛道");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("裁判员就位");
            SmallTool.sleepMills(200);
            return "裁判就位了";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("发令员就位");
            SmallTool.sleepMills(100);
            return "发令员就位了";
        }),(f1,f2) -> {
            SmallTool.printTimeAndThread("发令枪响了");
            SmallTool.sleepMills(100);
            return String.format("%s + %s 发令枪响了",f1,f2);
        });
        SmallTool.printTimeAndThread("小卓准备起跑");
        SmallTool.printTimeAndThread(String.format("%s,小卓比赛中",cf1.join()));
    }
//---------------------------------------------

    /**
     * 场景：比赛结束去领奖 等人颁发奖牌 等待期间呆呆站在领奖台上，拿完奖牌庆祝
     * 同thenCompose类似
     * 1. thenApply（）转换的是泛型中的类型，是同一个CompletableFuture
     *     它的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>。
     * 2. thenCompose（）用来连接两个CompletableFuture，是生成一个新的CompletableFuture。
     */
    @Test
    public void raceEndAsync01() {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        SmallTool.printTimeAndThread("比赛完了,小卓名列前茅要去领奖了");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("工作人员去拿奖牌");
            SmallTool.sleepMills(200);
            return "二等奖";
        }).thenApplyAsync(medal -> {
            SmallTool.printTimeAndThread("颁奖人就位");
            SmallTool.sleepMills(100);
            return String.format("颁发%s奖牌",medal);
        },executorService);

        SmallTool.printTimeAndThread("小卓站在领奖台上");
        SmallTool.printTimeAndThread(String.format("%s,小卓得奖了",cf1.join()));
    }

    /**
     * 场景：领奖完毕 小卓要回家休息 太累了准备在打车app打车回家 附近ab两车都选择挣这份钱
     */
    @Test
    public void raceEndAsync02() {
        SmallTool.printTimeAndThread("小卓打车app下单");
        SmallTool.printTimeAndThread("等待a车 或 b车 接单");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("a车司机接单？");
            SmallTool.sleepMills(200);
            return "a车接单";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("b车司机接单？");
            SmallTool.sleepMills(300);
            return "b车接单";
        }),quick -> {
            if(quick.startsWith("a")){
                throw new RuntimeException("a司机临时不接了");
            }
            return quick;
        }).exceptionally(e -> {
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("小卓决定自己开车");
            return "出发了";
        });
        SmallTool.printTimeAndThread(String.format("%s,小卓开车回家",cf1.join()));
    }










//---------------------------------------------
    @Test
    public void testCompletableThenRunAsync() throws Exception{
        long startTime = System.currentTimeMillis();
        CompletableFuture<Void> cp1 = CompletableFuture.runAsync(() -> {
            //任务1
            try {
                demoTask.taskOne();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> cp2 = CompletableFuture.runAsync(() -> {
            //任务2
            try {
                demoTask.taskTwo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> cp3 = CompletableFuture.runAsync(() -> {
            //任务3
            try {
                demoTask.taskThere();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        cp1.get();
        cp2.get();
        cp3.get();
        //模拟主程序耗时时间
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    @Test
    public void testCompletableThenCombine() throws ExecutionException, InterruptedException {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //开启异步任务1
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务1，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务1结束");
            return result;
        }, executorService);

        //开启异步任务2
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务2，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务2结束");
            return result;
        }, executorService);

        //任务组合
        CompletableFuture<Integer> task3 = task.thenCombineAsync(task2, (f1, f2) -> {
            System.out.println("执行任务3，当前线程是：" + Thread.currentThread().getId());
            System.out.println("任务1返回值：" + f1);
            System.out.println("任务2返回值：" + f2);
            return f1 + f2;
        }, executorService);

        Integer res = task3.get();
        System.out.println("最终结果：" + res);
    }

    @Test
    public void testCompletableThenCombine01() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务1，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务1结束");
            return result;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务2，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务2结束");
            return result;
        }), (f1, f2) -> {
            System.out.println("执行任务3，当前线程是：" + Thread.currentThread().getId());
            return f1 + f2;
        });
        System.out.println("task.join() = " + task.join());
    }
}