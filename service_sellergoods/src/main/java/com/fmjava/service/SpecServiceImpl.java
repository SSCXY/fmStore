package com.fmjava.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.specification.SpecificationDao;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.core.pojo.specification.SpecificationQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpecServiceImpl implements SpecService{
    @Autowired
    private SpecificationDao specificationDao;
    @Override
    public PageResult findPage(Specification spec, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        SpecificationQuery specQuery = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = specQuery.createCriteria();
        if (spec != null){
           if (spec.getSpecName() != null && !"".equals(spec.getSpecName())){
               criteria.andSpecNameLike("%" + spec.getSpecName() + "%");
           }
        }
        Page<Specification> specList =(Page<Specification>)specificationDao.selectByExample(specQuery);

        return new PageResult(specList.getResult(),specList.getTotal());
    }
}
