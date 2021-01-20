package com.fmjava.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    BrandService brandService;

    @RequestMapping("/findAllBrand")
    public List<Brand> findAllBrand(){
        return brandService.findAllBrand();
    }

    @RequestMapping("/findPage")
    public PageResult finPage(Integer page, Integer rows){
        return brandService.findPage(page, rows);
    }

    @RequestMapping("/add")
    private Result add(@RequestBody Brand brand){
        try {
            brandService.add(brand);
            return new Result(true,"保存成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"保存失败");
        }
    }

    @RequestMapping("/findById")
    private Brand findById(Long id){
        return brandService.findBrandWithId(id);
    }

    @RequestMapping("/update")
    private Result update(@RequestBody Brand brand){
        System.out.println(brand);
        try {

            brandService.update(brand);
            return new Result(true,"保存成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"保存失败");
        }
    }


}
