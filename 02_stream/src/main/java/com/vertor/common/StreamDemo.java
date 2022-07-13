package com.vertor.common;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wlz
 * @date 2022/7/4 10:05
 * 为了OptionalDemo的练习 main方法注释掉
 */

public class StreamDemo {

    public static void main(String[] args) {

        //test01();
        //test02();
        //test03();
        //test04();
        //test05();
        //test06();
        //test07();
        //test08();
        //test09();
        //test10();
        //test11();
//        test12();

    }

    /**
     * parallel() 实现多线程并行流
     * parallelStream可直接实现
     *
     * peek():Stream流提供的检验方法 作为中间件实现输出
     * 此处是用来查看当前线程是哪个
     */
    private static void test12() {
            Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            Integer sum = stream.parallel()
                    .peek(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer num) {
                            System.out.println(num+Thread.currentThread().getName());
                        }
                    })
                    .filter(num -> num > 5)
                    .reduce((result, ele) -> result + ele)
                    .get();
            System.out.println(sum);
        }

    private static void test11() {

        List<Author> authors1 = getAuthors();
        Stream<Author> authorStream = authors1.stream();
        authorStream.distinct()
                .filter(author -> author.getAge() > 17 || author.getName().length() >1)
                .forEach(System.out::println);
        authorStream.filter(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge()>17;
            }
        }.and(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getName().length()>1;
            }
        })).forEach(System.out::println);


        //使用reduce求所有作者年龄的和
        List<Author> authors = getAuthors();
        Integer result = authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce(0, Integer::sum);
        System.out.println(result);

        //使用reduce求所有作者中年龄的最大值
        authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }

    /**
     * .sorted() 对比排序 可以用min替换
     */
    private static void test10() {
        //获取一个年龄最小的作家，并输出他的姓名。
        List<Author> authors = getAuthors();
        /*authors.stream()
                .distinct()
                .sorted((o1,o2) -> o1.getAge() - o2.getAge())
                .limit(1)
                .forEach(author -> System.out.println(author.getName()));*/

        authors.stream()
                .distinct()
                .min((o1, o2) -> o1.getAge() - o2.getAge())
                .ifPresent((author -> System.out.println(author.getName())));


    }

    /**
     * .collect()数据转集合  三种转换形式
     */
    private static void test09() {
        //获取一个存放所有作者名字的List集合
        List<Author> authors = getAuthors();
        List<String> collect1 = authors.stream()
                .map(Author::getName)
                .distinct()
                .collect(Collectors.toList());

        //获取一个所有书名的Set集合。
        Set<String> collect2 = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(Book::getName)
                .collect(Collectors.toSet());

        //获取一个Map集合，map的key为作者名，value为List<Book>
        Map<String, List<Book>> collect03 = authors.stream()
                .distinct()
                .collect(Collectors.toMap(Author::getName, Author::getBooks));
    }

    /**
     * max min
     */
    private static void test08() {
//        分别获取这些作家的所出书籍的最高分和最低分并打印
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(Book::getScore)
                .distinct()
                .max((o1, o2) -> o1 - o2);
        System.out.println(max);

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(Book::getScore)
                .distinct()
                .min((o1, o2) -> o1 - o2);
        System.out.println(min);
    }

    /**
     * map使用
     * map数据类型拆装箱三种：mapToInt mapToLong mapToDouble
     * flatmap一样
     */
    private static void test07() {
        List<Author> authors = getAuthors();
        authors.stream()
              /*  //直接在作家集合中获取作家名字
                .distinct()
                .forEach(author -> System.out.println(author.getName()));*/

               /* //先获取名字集合再去重输出
                  .map(Author::getName)
                  .distinct()
                  .forEach(System.out::println);*/

                //map数据类型拆装箱
                .mapToInt(Author::getAge)
                .distinct()
                .peek(new IntConsumer() {
                    @Override
                    public void accept(int value) {

                    }
                })
                .forEach(System.out::println);

    }

    private static void test06() {
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
//                .map(book -> book.getCategory().split(","))
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * sorted 排序
     * limit 取前几条
     * skip 去除前几条
     */
    private static void test05() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted((o1,o2) ->o2.getAge() - o1.getAge() )
                .limit(2)
                .skip(1)
                .forEach(author -> System.out.println(author.getAge()));
    }


    /**
     * map用来获取数据 不同于filter筛选数据
     */
    private static void test04() {
        List<Author> authors = getAuthors();
        authors.stream()
                .map(Author::getAge)
                .map(age -> age + 10)
                .forEach(System.out::println);
    }

    /**
     * 双列集合处理：
     * 先把双列转换为单列
     */
    private static void test03() {

        Map<String ,Integer> map = new HashMap<>(16);
        map.put("地狱前线",12);
        map.put("才不怕呢",13);
        map.put("财富大师傅",14);

        Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();
        stream.distinct()
                .filter(x -> x.getValue() < 14)
                .forEach(System.out::println);
    }

    /**
     * 数组准换成流
     * 两种方法 Arrays.stream()  Stream.of()
     */
    private static void test02() {
        Integer[] arr = {1,2,2,3,4,5,6,7,8,9,10};

        Stream.of(arr)
                .distinct()
                .filter(x -> x < 7)
                .forEach(System.out::println);

        Arrays.stream(arr)
                .distinct()
                .filter(x -> x < 7)
                .forEach(System.out::println);
    }

    /**
     * 单列集合转换
     */
    private static void test01() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getAge()));
    }

    /**
     * 数据模拟
     * @return
     */
    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        return new ArrayList<>(Arrays.asList(author,author2,author3,author4));
    }
}