package cn.itsource.controller.user;

import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.param.LoginParam;
import cn.itsource.controller.utils.BindingResultUtil;
import cn.itsource.entity.User;
import cn.itsource.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Api(tags = "登录用户的controlelr")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation(value = "登录用户的接口")
    public ResponseResult login(
            //@Validated开启验证
            @Validated  @RequestBody LoginParam loginParam, BindingResult bindingResult, HttpServletRequest request){
        //校验
        BindingResultUtil.bindingResult(bindingResult);
        User login = userService.login(loginParam.getUsername(), loginParam.getPassword());
        request.getSession().setAttribute(Constant.User.SESSION_KEY, login);
        return ResponseResult.success(login);
    }
}
