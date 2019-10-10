package cn.itsource.service;

import cn.itsource.common.ResponseResult;
import cn.itsource.entity.Employee;
import cn.itsource.param.LoginParam;
import cn.itsource.param.PhoneLoginParam;
import cn.itsource.param.PhoneParam;

public interface EmployeeService {


    ResponseResult login(LoginParam loginParam);

    ResponseResult selectByPhone(PhoneParam phone);

    ResponseResult selectByPhoneCode(PhoneLoginParam phoneLoginParam);

}
