package cn.itsource.controller.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

/*工厂类 里面生成对应的权限*/
public class PermissionMapFactory {

    private Map<String,String> createPermission(){
        //因为权限是由 顺序的 LinkedHashMap是有序的
        Map<String, String> permissionMap = new LinkedHashMap<>();

        //anon 不需要登录，可以访问
        permissionMap.put("/login","anon" );
        permissionMap.put("/user/listpage/**", "anon");
        //需要权限才能访问
        permissionMap.put("/user/user/*", "perms[user:user]");
        //其他拦截必须写到最后
        permissionMap.put("/**","authc" );
        return permissionMap;
    }
}
