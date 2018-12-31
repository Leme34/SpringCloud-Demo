package com.lee.client;

import com.lee.pojo.TblEmp;
import org.springframework.stereotype.Component;

/**
 * feign的熔断接口实现类
 */
@Component   //注入容器
public class EmpClientDeafultFallback implements EmpClient {

    //熔断服务时调用的方法
    @Override
    public TblEmp queryById(Integer id) {
        TblEmp emp = new TblEmp();
        emp.setEmpName("熔断器触发，员工查询出现异常!");
        return emp;
    }
}
