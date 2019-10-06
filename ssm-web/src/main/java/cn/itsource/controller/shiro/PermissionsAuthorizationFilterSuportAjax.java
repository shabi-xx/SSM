package cn.itsource.controller.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionsAuthorizationFilterSuportAjax extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            //登录成功后没有权限的操作
            //1.转成http的请求与响应操作
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            //2.根据请求确定是什么请求
            String xRequestedWith = httpRequest.getHeader("X-Requested-With");
            if (xRequestedWith != null &&"XMLHttpRequest".equals(xRequestedWith)) {
                //3.在这里就代表是ajax请求
                //表示ajax请求 {"code":"001","message":"没有权限"}
                httpResponse.setContentType("text/json; charset=UTF-8");
                httpResponse.getWriter().print("{\"code\":\"001\",\"message\":\"没有权限\"}");
            }else {
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }

        return false;
    }
    //支持restful风格
    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        String requestURI = this.getPathWithinApplication(request);
        // path: url--method eg: http://api/menu--GET   需要解析出path中的url和httpMethod
        String[] strings = path.split("--");
        if (strings.length <= 1) {
            // 分割出来只有URL
            return this.pathsMatch(strings[0], requestURI);
        } else {
            // 分割出url+httpMethod,判断httpMethod和request请求的method是否一致,不一致直接false
            String httpMethod = WebUtils.toHttp(request).getMethod().toUpperCase();
            return httpMethod.equals(strings[1].toUpperCase()) && this.pathsMatch(strings[0], requestURI);
        }
    }
}
