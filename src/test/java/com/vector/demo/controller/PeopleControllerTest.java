package com.vector.demo.controller;

import com.baomidou.mybatisplus.core.conditions.interfaces.Compare;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class PeopleControllerTest {

    @Test
    void localtime() {
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localTime + "   " + localDateTime);
    }

    @Test
    void lambdaTest() {

        Comparator<Integer> comparable = (x, y) -> Integer.compare(x,y);
        System.out.println(comparable.compare(10, 11));
    }

    /**
     *
     */
    @Test
    void lambdaANDRemoveIf() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.removeIf("2"::equals);
        System.out.println(stringList.toString());
    }

    @Test
    void niCai() {
        List<String> stringList = new ArrayList<>();
        stringList.add("11");
        stringList.add("22");
        stringList.add("2");
        stringList.add("21");
        stringList.add("30");
        Collections.sort(stringList);
        stringList.forEach(System.out::println);
    }
}
