package cn.itsource.controller.shiro;

import cn.itsource.entity.User;
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

    @Autowired
    private UserService userService;

    /*授权管理 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //1.当前登录用户，账号
        User loginUser = (User) principal.getPrimaryPrincipal();
        //String userCode = principal.toString();
        //2.根据用户 在数据库 获取角色信息
        Set<String> roles = this.getRoles(loginUser.getUsername());
        Set<String> perms = this.getPerms(loginUser.getUsername());
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
        User loginUser = userService.findByUsername(username);
        //4.密码没找到 说明不存在
        if(loginUser==null){
            return null;
        }
        //5.获取盐，用于对密码在加密算法(MD5)的基础上二次加密ֵ
        ByteSource byteSalt = ByteSource.Util.bytes("hzw");
        //第一个参数是 主体  第二个 密码  第三个 盐值 第四个 是名字 ==>主体和密码 换为登录用户
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(),byteSalt,getName());
        //7. 返回给调用login(token)方法
        return info;
    }

}
