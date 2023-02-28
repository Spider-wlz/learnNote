package com.idss.demo.controller;

import com.idss.demo.task.AsyncLogTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wlz
 * @date 2022/10/11 10:28
 */

@Slf4j
@RestController
public class AsyncTestController {

    @Autowired
    private AsyncLogTask asyncLogTask;

    @RequestMapping(value = "/login")
    public void queryUser( Integer userId) {
        log.info("======正常登陆操作======");
        asyncLogTask.recordLoginLog(userId);
    }
}