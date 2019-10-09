package cn.itsource.controller.user;

import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.param.LoginParam;
import cn.itsource.controller.utils.BindingResultUtil;
import cn.itsource.entity.User;
import cn.itsource.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@Api(tags = "登录用户的controlelr")
@CrossOrigin(allowCredentials = "true")
class LoginController1 {
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
        //shiro
        //1.拿到当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //2.如果当前用户没有登录 让他登录
        if(!currentUser.isAuthenticated()) {
            //创建一个用户密码令牌
            UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUsername(), loginParam.getPassword());
            token.setRememberMe(true);
            try {
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
        User loginUser = (User)currentUser.getPrincipal();
        //拿到session对象
        Session session = currentUser.getSession();
        //把用户放到session中
        session.setAttribute(Constant.User.SESSION_KEY,loginUser);
        return ResponseResult.success(loginUser);
    }
}
