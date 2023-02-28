package com.vector.demo.controller;

import com.vector.demo.response.Result;
import com.vector.demo.service.PeopleService;
import com.vector.demo.util.LocalCache;
import com.vector.demo.viewModel.PeopleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wlz
 */
@Slf4j
@RestController
@Api(tags = "ceshi")
@RequestMapping("/employee")
public class PeopleController {

    @Autowired
    public PeopleService peopleService;

    @GetMapping("/getPeople")
    public Result findName() {
        try {
            List<PeopleVo> peopleList = peopleService.findName();
            return Result.ok().data("people", peopleList).code(111).message("nicai");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/nicai")
    public String findNiCai() {
        return "ok";
    }

    @GetMapping("/cache")
    public Object testCache(@RequestBody Map<String, Object> param) {
        String key = param.get("key").toString();
        Object value = param.get("value");
        Integer expire = (Integer) param.get("expire");

        if (Objects.nonNull(value)) {
            LocalCache.getInstance().putValue(key, value, expire);
            return "缓存数据成功";
        }

        Object a = LocalCache.getInstance().getValue(key);
        return "缓存数据:" + a;
    }
}