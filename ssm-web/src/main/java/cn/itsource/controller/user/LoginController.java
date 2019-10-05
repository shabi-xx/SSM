package cn.itsource.controller.user;

import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.param.LoginParam;
import cn.itsource.controller.utils.BindingResultUtil;
import cn.itsource.entity.User;
import cn.itsource.service.UserService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.CallNode;
import jdk.nashorn.internal.ir.Expression;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@Api(tags = "登录用户的controlelr")
@CrossOrigin(allowCredentials = "true")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation(value = "登录用户的接口")
    public ResponseResult login(
            //@Validated开启验证
            @Validated  @RequestBody LoginParam loginParam, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response){
        //校验
        BindingResultUtil.bindingResult(bindingResult);
        log.info(loginParam.getUsername()+"-----------"+loginParam.getPassword());
        //shiro
        //1.拿到当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //2.如果当前用户没有登录 让他登录
        if(!currentUser.isAuthenticated()) {
            //创建一个用户密码令牌
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUsername(), loginParam.getPassword());
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                log.info(Constant.User.USERNAME_ERROR);
                return ResponseResult.fail(Constant.User.LOGIN_ERROR);
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                log.info(Constant.User.PASSWORD_ERROR);
                return ResponseResult.fail(Constant.User.LOGIN_ERROR);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return ResponseResult.fail(Constant.User.LOGIN_ERROR);
            }
        }
        /*User login = userService.login(loginParam.getUsername(), loginParam.getPassword());
        request.getSession().setAttribute(Constant.User.SESSION_KEY, login);*/
        //return ResponseResult.success(login);

        /*获取前台传递的cookie*/
        //stream.of 把数组转为流
        Stream.of(request.getCookies())
                //filter 表示过滤  cookie为每一个元素
                .filter(cookie ->cookie.getName().equals("ssm"))
                .forEach(cookie -> System.out.println(cookie.getValue()));

        //拿到session对象
        Session session = currentUser.getSession();
        //把用户放到session中
        session.setAttribute("loginUser",loginParam);
        return ResponseResult.success(loginParam);
    }
}
