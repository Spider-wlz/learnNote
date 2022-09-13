package com.vector.demo.service;

import com.vector.demo.viewModel.PeopleVo;

import java.util.List;
/**
 * @author wlz
 * @date 2022/3/18 17:19
 */

public interface PeopleService {

    /**
     * 统计age大于30 工资大于一万的员工
     * @return list
     */
    List<PeopleVo> findName();
}