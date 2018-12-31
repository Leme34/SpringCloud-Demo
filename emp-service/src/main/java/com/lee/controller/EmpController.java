package com.lee.controller;

import com.lee.pojo.TblEmp;
import com.lee.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @RequestMapping("/{id}")
    public TblEmp queryEmp(@PathVariable("id")Integer id, HttpServletRequest request){
        //测试负载均衡
        System.out.println("当前服务被请求");
        return empService.queryById(id);
    }

}
