package com.idss.demo.config;

import java.util.StringJoiner;

/**
 * @author wlz
 * @date 2022/10/11 14:43
 */

public class SmallTool {

    /**
     * 该休息了
     * @param mills
     */
    public static void sleepMills(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当前时间戳 + 线程信息 + 消息
     * @param tag
     */
    public static void printTimeAndThread(String tag) {
        String result = new StringJoiner("\t|\t")
                .add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(Thread.currentThread().getName())
                .add(tag)
                .toString();
        System.out.println(result);
    }
}