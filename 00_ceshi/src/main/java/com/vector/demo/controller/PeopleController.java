package com.vector.demo.controller;

import com.vector.demo.response.Result;
import com.vector.demo.service.PeopleService;
import com.vector.demo.viewModel.PeopleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result findName(){
        try{
            List<PeopleVo> peopleList = peopleService.findName();
            return Result.ok().data("people",peopleList).code(111).message("nicai");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/nicai")
    public String findNiCai(){
     return "ok";
    }
}