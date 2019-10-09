package cn.itsource.controller.employee;

import cn.itsource.FastJsonTools;
import cn.itsource.RedisUtils;
import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.cookie.CookieUtil;
import cn.itsource.controller.utils.BindingResultUtil;
import cn.itsource.entity.Employee;
import cn.itsource.param.LoginParam;
import cn.itsource.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/employee")
@Api(tags = "登录的controller")
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
}
