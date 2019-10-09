package cn.itsource.common;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS("1","请求成功"),
    FAIL("2","请求失败"),
    LOGIN_FAIL("3","没有登录");
    //编号
    private String code;//404 500 400 403
    //对应的信息
    private String message;

    ResultEnum(String code,String message){
        this.code = code;
        this.message = message;
    }
}
