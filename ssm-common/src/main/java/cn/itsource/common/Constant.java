package cn.itsource.common;

public interface Constant {

    String CUSTOMEXCPETIONMESSAGE="系统自定义异常";


    int SUSSESS_CODE = 001 ;
    interface Permission{
        //权限的 redis key的前缀
        String PERMISSION_KEY = "PERMISSION_KEY";
    }

    interface User{
        String SESSION_KEY = "key";
        String LOGIN_SESSION_KEY = "key";
        String LOGIN_COOKIE_KEY = "login_key";
        String LOGIN_ERROR ="用户名或者密码错误";
        String NO_LOGIN ="用户未登录";


        String USERNAME_ERROR ="用户名错误";
        String PASSWORD_ERROR ="密码错误";
    }


}
