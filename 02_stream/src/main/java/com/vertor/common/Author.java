package com.vertor.common;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author wlz
 * @date 2022/7/4 9:55
 */

@Slf4j
@Getter @Setter @ToString
@Accessors(chain = true)
@AllArgsConstructor
@EqualsAndHashCode()
public class Author {

    @Value("id")
    private Long id;

    @Value("姓名")
    private String name;

    @Value("年龄")
    private Integer age;

    @Value("简介")
    private String intro;

    @Value("作品")
    private List<Book> books;
}