package com.lee.client;

import com.lee.pojo.TblEmp;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * feign的熔断接口统一实现类
 */
@Component  //注入容器
public class EmpClientFallbackFactory implements FallbackFactory<EmpClient> {

    @Override
    public EmpClient create(Throwable cause) {
        return new EmpClient() {
            @Override
            public TblEmp queryById(Integer id) {
                TblEmp emp = new TblEmp();
                emp.setEmpName("熔断器触发，员工查询出现异常!");
                return emp;
            }
        };
    }
}
