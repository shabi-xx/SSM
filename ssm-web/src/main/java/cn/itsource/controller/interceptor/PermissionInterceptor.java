package cn.itsource.controller.interceptor;

import cn.itsource.FastJsonTools;
import cn.itsource.RedisUtils;
import cn.itsource.ThreadLocalUtil;
import cn.itsource.common.Constant;
import cn.itsource.common.NeedLogin;
import cn.itsource.common.NeedPermission;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.cookie.CookieUtil;
import jdk.nashorn.internal.objects.annotations.Where;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class PermissionInterceptor implements HandlerInterceptor {

    // 在调用controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //4.获取到前台传递的路径
            String requestURL = request.getRequestURI();
            //1.获得要执行的方法体
            HandlerMethod handler1 = (HandlerMethod) handler;
            //2.获得方法的对象
            Method method = handler1.getMethod();
            //获取到前台的请求方式 是get 还是post 还是delete
           log.info(request.getMethod());
            //3.判断方法是否有注解
            if (method.isAnnotationPresent(NeedPermission.class)) {
                //5.从当前线程中获取到 key
                String username = ThreadLocalUtil.get();
                //6.从redis中拿去到权限路径
                Jedis jedis = RedisUtils.getJedis();
                String urlStr = jedis.get(Constant.Permission.PERMISSION_KEY + username);
                RedisUtils.closeJedis(jedis);
                //7.把权限字符串 转为集合
                List<String> urls = FastJsonTools.getArrayJson(urlStr);
                for (String url : urls) {
                    String urla = url;
                    String requesturl = requestURL;
                    while (urla.endsWith("/*")){ //如果权限路径 最后有/*的
                        //将url 的值截取到 最后一个/的位置
                        urla = urla.substring(0,urla.lastIndexOf("/"));
                        requesturl = requesturl.substring(0,requesturl.lastIndexOf("/"));
                    }
                    if(requesturl.endsWith(urla)){
                        log.info("登录用户------"+username+"--------有权限");
                        return true;
                    }
                }
                log.info("登录用户------"+username+"--------mei有权限");
                return false;
            }
            return true;
        }else {
            return true;
        }
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
