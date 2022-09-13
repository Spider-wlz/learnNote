package com.vector.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @author wlz
 */
@Data
public class PeopleBean {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private int age;
    private Double salary;


}