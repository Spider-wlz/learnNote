package com.vertor.common;

import java.util.Optional;

/**
 * @author wlz
 * @date 2022/7/7 16:09
 */

public class OptionalDemo {

    public static void main(String[] args) {
        test02();
        //test01();
        //test00();
        test03();
    }

    private static void test03() {
        
    }

    /**
     * objectOptional.orElseThrow抛出异常
     *
     */
    private static void test02() {
        Optional<Author> objectOptional = Optional.of(null);
        try {
            Author author = objectOptional.orElseThrow(() -> new RuntimeException("数据为Null"));
            System.out.println("author = " + author);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    /**
     * .orElseGet(new A); new一个对象 如果传过来的值为空就用new的 不为空就用传过来的
     */
    private static void test01() {
        Optional<Author> authorOptional = getAuthorOptional();
        Author author = authorOptional.orElseGet(() -> new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null));
        System.out.println("author = " + author);
    }

    /**
     * 直接把返回值做Optional泛型封装
     * @return
     */
    public static Optional<Author> getAuthorOptional(){
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        return Optional.ofNullable(author);
//        return author == null ? Optional.empty() : Optional.of(author);
    }





    /**
     * 不对Author类做封装处理
     */
    private static void test00() {
        Author authors = getAuthor();
        Optional.ofNullable(authors)
                .ifPresent(author -> System.out.println(author.getName()));
    }
    /**
     * 对应test00()
     * @return
     */
    public static Author getAuthor(){
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        return author;
    }
}