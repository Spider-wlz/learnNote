package com.vector.demo.service.imp;

import com.vector.demo.bean.PeopleBean;
import com.vector.demo.mapper.PeopleMapper;
import com.vector.demo.service.PeopleService;
import com.vector.demo.viewModel.PeopleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wlz
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    public static final Double SALARY_SUM = 10000.0;
    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public List<PeopleVo> findName() {
        List<PeopleBean> p1 = peopleMapper.findPeople();
        List<PeopleVo> pvList = new ArrayList<>();
        for(PeopleBean p : p1){
            PeopleVo pv = null;
            Double upSalary = p.getSalary();
            if(upSalary >= SALARY_SUM){
                pv.setSalary(upSalary / 10000 + "万");
            }else{
                pv.setSalary(upSalary + "");
            }
            //赋值
            BeanUtils.copyProperties(p,pv);
            pvList.add(pv);
        }
        return pvList;
    }
}