package com.fmjava.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.good.BrandDao;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.core.pojo.good.BrandQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<Brand> findAllBrand() {
       return   brandDao.selectByExample(null);
    }

    @Override
    public PageResult findPage(Integer page, Integer pageSize, Brand brand) {

        PageHelper.startPage(page, pageSize);
        BrandQuery brandQuery = new BrandQuery();
        BrandQuery.Criteria criteria = brandQuery.createCriteria();
        String brandName = brand.getName();
        String brandFirsChar = brand.getFirstChar();
        if(brandName != null && ! "".equals(brandName)){
            criteria.andNameLike("%" + brandName + "%");
        }
        if (brandFirsChar != null && ! "".equals(brandFirsChar)){
            criteria.andFirstCharLike("%" + brandFirsChar + "%");
        }
        brandQuery.setOrderByClause("id desc");
        Page<Brand> brandList = (Page<Brand>) brandDao.selectByExample(brandQuery);

        return new PageResult(brandList.getResult(), brandList.getTotal());
    }

    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);
    }

    @Override
    public Brand findBrandWithId(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Long[] ids) {
        BrandQuery brandQuery = new BrandQuery();
        BrandQuery.Criteria criteria = brandQuery.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        brandDao.deleteByExample(brandQuery);
    }


}
