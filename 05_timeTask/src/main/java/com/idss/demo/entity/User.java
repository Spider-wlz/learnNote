package com.idss.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wlz
 * @date 2022/10/9 17:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int card;
    private String name;
}