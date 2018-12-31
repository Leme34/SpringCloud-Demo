package com.lee.service;

import com.lee.mapper.TblEmpMapper;
import com.lee.pojo.TblEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Service
public class EmpService {

    @Autowired
    private TblEmpMapper empMapper;


    public TblEmp queryById(Integer id){
        // 为了演示超时现象，我们在这里然线程休眠,时间随机 0~2000毫秒
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return empMapper.selectByPrimaryKey(id);
    }

}
