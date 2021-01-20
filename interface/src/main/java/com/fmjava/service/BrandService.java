package com.fmjava.service;

import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.good.Brand;

import java.util.List;

public interface BrandService {
    public List<Brand> findAllBrand();

    public PageResult findPage(Integer page, Integer rows, Brand brand);

    public void add(Brand brand);

    public Brand findBrandWithId(Long id);

    public void update(Brand brand);

    public void delete(Long[] ids);

}
