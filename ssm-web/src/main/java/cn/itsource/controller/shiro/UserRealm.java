package cn.itsource.controller.shiro;

import cn.itsource.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
      /*  //当前登录用户，账号
        String userCode = principal.toString();
        System.out.println("当前登录用户:"+userCode);
        //获取角色信息
        List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>();

        roleList = userSer.findRoles(userCode);
        Set<String> roles = new HashSet<String>();

        if(roleList.size()>0){
            for(Map<String, Object> role : roleList){
                roles.add(String.valueOf(role.get("rcode")));
            }
        }else{
            System.out.println("当前用户没有角色！");
        }

        SimpleAuthorizationInfo info = null;
        info = new SimpleAuthorizationInfo(roles);
        return info;*/
      return null;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      /*  //1.将token转换为UsernamePasswordToken
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //2.获取token中的登录账户
        String userCode = userToken.getUsername();
        //3.查询数据库，是否存在指定的用户名和密码的用户(主键/账户/密码/账户状态/盐)
        us = null;
        us = userSer.findUserByUserCode(userCode);
        //4.1 如果没有查询到，抛出异常
        if( us == null ) {
            throw new UnknownAccountException("账户"+userCode+"不存在！");
        }
        if( us.getStatus() == 0){
            throw new LockedAccountException(us.getUsercode()+"被锁定！");
        }
        //4.2 如果查询到了，封装查询结果，
        Object principal = us.getUsercode();
        Object credentials = us.getPassword();
        String realmName = this.getName();
        String salt = us.getSalt();
        //获取盐，用于对密码在加密算法(MD5)的基础上二次加密ֵ
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, byteSalt, realmName);
        //5. 返回给调用login(token)方法
        return info;*/
        return null;
    }

}
