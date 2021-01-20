package com.fmjava.service;


import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.specification.Specification;

public interface SpecService {
    public PageResult findPage(Specification spec, Integer page, Integer pageSize);
}
