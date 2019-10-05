package cn.itsource.common;

public interface Constant {

    String CUSTOMEXCPETIONMESSAGE="系统自定义异常";


    int SUSSESS_CODE = 001 ;

    interface User{
        String SESSION_KEY = "key";
        String LOGIN_ERROR ="用户名或者密码错误";
        String USERNAME_ERROR ="用户名错误";
        String PASSWORD_ERROR ="密码错误";
    }


}
