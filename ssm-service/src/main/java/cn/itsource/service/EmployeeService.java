package cn.itsource.service;

import cn.itsource.common.ResponseResult;
import cn.itsource.param.LoginParam;

public interface EmployeeService {


    ResponseResult login(LoginParam loginParam);
}
