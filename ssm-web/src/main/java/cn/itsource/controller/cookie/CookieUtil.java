package cn.itsource.controller.cookie;

import cn.itsource.common.Constant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    //创建cookie
    public static void addCookie(String key, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(key,value);
        //cookie最大存活时间  以秒为单位
        //如果前台点击 记住我 可以设置一个cookie时间
        // 或者 不记住 就用默认时间  关闭浏览器的时候 cookie消失
        cookie.setMaxAge(60*60*24*7);
        //防止cookie攻击
        cookie.setHttpOnly(true);
        //domain 设置二级域名  不同项目 都可以取到cookie
        //cookie.setDomain(".baidu.com")
        //path针对相同的项目 不同路径 表示项目根路径
        cookie.setPath("/");
        //把cookie放在响应中
        response.addCookie(cookie);
    }

    //获取cookie
    public static String getLoginCookieValue(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return null;
        }
        for(Cookie cookie:cookies){
            //如果 cookie的 name = key
            if(cookie.getName().equals(Constant.User.LOGIN_COOKIE_KEY)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
