package cn.itsource.controller.interceptor;

import cn.itsource.FastJsonTools;
import cn.itsource.RedisUtils;
import cn.itsource.ThreadLocalUtil;
import cn.itsource.common.Constant;
import cn.itsource.common.NeedLogin;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.cookie.CookieUtil;
import cn.itsource.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    // 在调用controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //获得要执行的方法体
            HandlerMethod handler1 = (HandlerMethod) handler;
            //获得方法的对象
            Method method = handler1.getMethod();
            //判断方法是否有注解
            if (method.isAnnotationPresent(NeedLogin.class)) {
                //判断是否登录
                String loginCookieValue = CookieUtil.getLoginCookieValue(request);
                //是否有cookie
                if (loginCookieValue == null) {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(FastJsonTools.toJSONString(ResponseResult.loginFail(Constant.User.NO_LOGIN)));
                    return false;
                }
                if (loginCookieValue != null) {
                    //判断redis是否有
                    Jedis jedis = RedisUtils.getJedis();
                    String employee = jedis.get(loginCookieValue);
                    RedisUtils.closeJedis(jedis);
                    if (employee == null) {
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().write(FastJsonTools.toJSONString(ResponseResult.loginFail(Constant.User.NO_LOGIN)));
                        return false;
                    }
                    //如果有登录对象，把这个对象 放在当前线程中
                    ThreadLocalUtil.set(FastJsonTools.getJson(employee, Employee.class).getUsername());
                }

            }
        }
        return true;
    }

    // 在调用controller之前执行 有异常不执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    // 在调用controller之前执行 有没有异常都执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
