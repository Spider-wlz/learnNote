package com.idss.demo.task;

import com.idss.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wlz
 * @date 2022/10/9 17:02
 */

public class MyTest {
    public static void main(String[] args) {
        List<? extends Number> list = new ArrayList<>();
//        list.add();
        List<? super Number> listb = new ArrayList<>();
        listb.add(1);
    }
}