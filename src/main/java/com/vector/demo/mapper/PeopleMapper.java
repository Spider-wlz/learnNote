package com.vector.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.demo.bean.PeopleBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wlz
 */
@Repository
public interface PeopleMapper extends BaseMapper<PeopleBean> {

    /**
     * 统计age大于30 工资大于一万的员工
     * @return list
     */
    @Select("select * from people where age > 30 and salary > 5000.0")
    List<PeopleBean> findPeople ();
}
