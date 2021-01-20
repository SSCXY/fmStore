package com.fmjava.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.service.SpecService;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/spec")
@RestController
public class SpecController {
    @Reference
    private SpecService specService;

    @RequestMapping("/search")
    public PageResult search(Specification spec, Integer page, Integer pageSize){
        return specService.findPage(spec, page, pageSize);
    }
}
