package cn.itsource.controller.shiro.CORS;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*解决  CORS跨域问题*/

public class MyAuthenticationFilter extends FormAuthenticationFilter {


    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationFilter.class);

    public MyAuthenticationFilter() {
        super();
    }

    /*放行 OPTIONS  权限认证控制跳过OPTIONS方法，不让他做认证检查*/
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }


   /*解决401*/
   /*响应 跨域*/
   @Override
   protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
       HttpServletRequest httpServletRequest = (HttpServletRequest) request;
       HttpServletResponse httpServletResponse = (HttpServletResponse) response;
       httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin")); //标识允许哪个域到请求，直接修改成请求头的域
       httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");//标识允许的请求方法
       // 响应首部 Access-Control-Allow-Headers 用于 preflight request （预检请求）中，列出了将会在正式请求的 Access-Control-Expose-Headers 字段中出现的首部信息。修改为请求首部
       httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
       httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
       //给option请求直接返回正常状态
       if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
           httpServletResponse.setStatus(HttpStatus.OK.value());
           return false;
       }
       return super.preHandle(request, response);
   }
}

