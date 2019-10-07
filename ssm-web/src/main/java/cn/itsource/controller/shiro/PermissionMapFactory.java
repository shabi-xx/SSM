package cn.itsource.controller.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/*工厂类 里面生成对应的权限*/
public class PermissionMapFactory {

    private Map<String,String> createPermission(){
        //因为权限是由 顺序的 LinkedHashMap是有序的
        Map<String, String> permissionMap = new LinkedHashMap<>();

        //anon 不需要登录，可以访问
        permissionMap.put("/login","anon" );
        //permissionMap.put("/user/listpage/**", "anon");
        //需要权限才能访问
        //permissionMap.put("/user/user/*", "perms[user:user]");
        //其他拦截必须写到最后  这里改为自己的拦截器
        permissionMap.put("/**","myAuthenticationFilter" );
        return permissionMap;
    }
}
