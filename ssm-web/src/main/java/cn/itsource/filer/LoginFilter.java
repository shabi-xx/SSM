package cn.itsource.filer;

import cn.itsource.FastJsonTools;
import cn.itsource.RedisUtils;
import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.cookie.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginFilter extends HttpFilter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

       //拿到cookie
        String loginCookieValue = CookieUtil.getLoginCookieValue(request);
        if(loginCookieValue==null){
            log.info("亲没有登录哟");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(FastJsonTools.toJSONString(ResponseResult.loginFail(Constant.User.NO_LOGIN)));
            return;
        }
        if(loginCookieValue!=null){
            Jedis jedis = RedisUtils.getJedis();
            String employee = jedis.get(loginCookieValue);
            RedisUtils.closeJedis(jedis);
            if(employee==null){
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(FastJsonTools.toJSONString(ResponseResult.loginFail(Constant.User.NO_LOGIN)));
                return;
            }
        }
        //放过
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
