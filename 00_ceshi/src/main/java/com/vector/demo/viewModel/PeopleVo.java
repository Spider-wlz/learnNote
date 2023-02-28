package com.vector.demo.viewModel;

import lombok.Builder;
import lombok.Data;

/**
 * @author wlz
 * @date 2022/5/23 9:21
 */
@Data @Builder
public class PeopleVo {

    private String name;
    private int age;
    private String salary;
}