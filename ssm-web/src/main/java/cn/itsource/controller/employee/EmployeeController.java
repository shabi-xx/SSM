package cn.itsource.controller.employee;

import cn.itsource.FastJsonTools;
import cn.itsource.RedisUtils;
import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.cookie.CookieUtil;
import cn.itsource.controller.utils.BindingResultUtil;
import cn.itsource.entity.Employee;
import cn.itsource.param.LoginParam;
import cn.itsource.param.PhoneLoginParam;
import cn.itsource.param.PhoneParam;
import cn.itsource.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
@Api(tags = "登录的controller")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口",notes = "登录接口")
    public ResponseResult login(
            @Validated @RequestBody LoginParam loginParam, BindingResult bindingResult,
            HttpSession httpSession, HttpServletResponse response){
        BindingResultUtil.bindingResult(bindingResult);

        ResponseResult responseResult =  employeeService.login(loginParam);

        if(responseResult.getCode().equals("1")) {
            Employee employee = (Employee) responseResult.getData();
            //解决单点登录
            //1.把信息放在cookie
            CookieUtil.addCookie(Constant.User.LOGIN_COOKIE_KEY, employee.getUsername(), response);

        }
        return responseResult;
    }

    @PostMapping("/code")
    @ApiOperation(value = "获取验证码",notes = "获取验证码接口")
    public ResponseResult getCode(@Validated @RequestBody PhoneParam phone,BindingResult bindingResult){
        BindingResultUtil.bindingResult(bindingResult);
        log.info(phone.getPhone());

        ResponseResult responseResult = employeeService.selectByPhone(phone);
        return responseResult;
    }
    @PostMapping("/phoneLogin")
    @ApiOperation(value = "手机登录",notes = "手机登录")
    public ResponseResult phoneLogin(@Validated @RequestBody PhoneLoginParam phoneLoginParam, BindingResult bindingResult,HttpServletResponse response){
        BindingResultUtil.bindingResult(bindingResult);
        log.info(phoneLoginParam.getVcode());
        ResponseResult responseResult = employeeService.selectByPhoneCode(phoneLoginParam);

        if(responseResult.getCode().equals("1")) {
            Employee employee = (Employee) responseResult.getData();
            //解决单点登录
            //1.把信息放在cookie
            CookieUtil.addCookie(Constant.User.LOGIN_COOKIE_KEY, employee.getUsername(), response);
        }
        return responseResult;
    }
}
