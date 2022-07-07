package com.vector;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * @author wlz
 * @date 2022/7/2 10:42
 */

public class LambdaDemo01 {

    public static void main(String[] args) {

      /*  //1
        new Thread(() -> System.out.println("nicai2.0")).start();

        //2
        int i = calculateNum((a, b) -> a + b);
        System.out.println("匿名内部类的我：" + i);

        //3
        printNum(a -> a%2 == 0);

        //4
        //字符串间的操作：分割符
        String[] strings = typeConver(x -> x.split("-"));
        Arrays.stream(strings).forEach(System.out::println);

        //字符串转int型 泛型两个参数 第一个是入参 第二个是返回类型 （源码有）
        Integer result = typeConver((x) -> Integer.valueOf(x));
        System.out.println(result);
        //方法引用
        typeConver(Integer::valueOf);

        //5
        foreachArr(System.out::println);*/
    }

    public static void foreachArr(IntConsumer consumer){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for(int i : arr){
            consumer.accept(i);
        }
    }

    public static <R> R typeConver(Function<String,R> function){
        String  str = "1-23-5";
        R result = function.apply(str);
        return result;
    }

    public static void printNum(IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {     
            if(predicate.test(i)){
                System.out.println(i);
            }
        }
    }

    public static int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a,b);
    }
}