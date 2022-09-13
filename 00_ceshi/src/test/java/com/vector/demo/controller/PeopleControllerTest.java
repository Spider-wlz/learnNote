package com.vector.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PeopleControllerTest {


    /**
     * new子类 隐式调用父类super(this)
     */
    @Test
    void FuAndZi() {
        Zi zi = new Zi();
    }
    class Fu {
        int a = 10;

        public void Fup() {
            System.out.println("父类a = " + a);
        }
        Fu() {
            Fup();
        }
    }
    class Zi extends Fu{
        int a = 20;
        public void Fup() {
            System.out.println("子类a = " + a);
        }
        Zi() {
            Fup();
        }
    }

    @Test
    void localtime() {
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localTime + "   " + localDateTime);
    }

    @Test
    void lambdaTest() {
        Comparator<Integer> comparable = (x, y) -> Integer.compare(x, y);
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

    @Test
    void ggChe() {
        List<String> result = new ArrayList<>();
    }
}
