package cn.itsource.controller.shiro;

import cn.itsource.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {

    /*授权管理 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //1.当前登录用户，账号
        String username = (String) principal.getPrimaryPrincipal();
        //String userCode = principal.toString();
        //2.根据用户 在数据库 获取角色信息
        Set<String> roles = this.getRoles(username);
        Set<String> perms = this.getPerms(username);
        //3.创建AuthorizationInfo对象(把角色与权限放进去)
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;
    }
    //假设我通过用户名到数据库中获取角色和权限
    public Set<String> getRoles(String username){
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        roles.add("it");
        return roles;
    }
    public Set<String> getPerms(String username){
        Set<String> perms = new HashSet<String>();
        perms.add("employee:*");
        // perms.add("employee:save");
        // perms.add("department:save");
        return perms; }

    /*登录管理*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.将token转换为UsernamePasswordToken 用户密码令牌
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //2.获取token中的登录账户
        String username = userToken.getUsername();
        //3.查询数据库，是否存在指定的用户名和密码的用户(主键/账户/密码/账户状态/盐)
        String password = this.login(username);
        //4.密码没找到 说明不存在
        if(password==null){
            return null;
        }
        //5.获取盐，用于对密码在加密算法(MD5)的基础上二次加密ֵ
        ByteSource byteSalt = ByteSource.Util.bytes("hzw");
        /*第一个参数是 主体  第二个 密码  第三个 盐值 第四个 是名字*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, byteSalt, getName());
        //7. 返回给调用login(token)方法
        return info;
    }

    //假设一个账户和密码
    public String login(String username){
        if("admin".equals(username)){
            //这个密码是123456
            return "dacd29653a886405c26d2486489a07cf";
        }
        return null;
    }
}
