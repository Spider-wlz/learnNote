package com.vertor.common;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @author wlz
 * @date 2022/7/4 9:55
 */
@Slf4j
@Data
@Getter @Setter @ToString
@AllArgsConstructor
@Accessors
@EqualsAndHashCode()
public class Book implements Serializable {

    @Value("id")
    private Long id;

    private String name;

    @Value("分类")
    private String category;

    @Value("评分")
    private Integer score;

    @Value("简介")
    private String intro;
}