package cn.itsource.common;

import lombok.Getter;

@Getter//null是否返回
public class ResponseResult<T> {
    //返回编码
    private String code;
    //信息
    private String message;
    //携带的数据
    private T data;

    private  ResponseResult(String code){
        this.code = code;
    }
    private ResponseResult(String code,String message){
        this.code = code;
        this.message = message;
    }

    private ResponseResult(String code,T data){
        this.code = code;
        this.data = data;
    }
    private ResponseResult(String code,String message,T data){
        this.code = code;
        this.data = data;
        this.message = message;
    }
    //请求成功
    public static ResponseResult success(){
        return new ResponseResult(ResultEnum.SUCCESS.getCode());
    }
    public static ResponseResult success(String message){
        return new ResponseResult(ResultEnum.SUCCESS.getCode(),message);
    }
    public static<T> ResponseResult success(T data){
        return new ResponseResult(ResultEnum.SUCCESS.getCode(),data);
    }
    public static<T> ResponseResult success(String message, T data){
        return new ResponseResult(ResultEnum.SUCCESS.getCode(),message,data);
    }
    //请求错误
    public static ResponseResult fail(){
        return new ResponseResult(ResultEnum.FAIL.getCode());
    }
    public static ResponseResult fail(String message){
        return new ResponseResult(ResultEnum.FAIL.getCode(),message);
    }
    public static<T> ResponseResult fail(T data){
        return new ResponseResult(ResultEnum.FAIL.getCode(),data);
    }
    public static<T> ResponseResult fail(String message, T data){
        return new ResponseResult(ResultEnum.FAIL.getCode(),message,data);
    }
    //没有登录
    public static ResponseResult loginFail(){
        return new ResponseResult(ResultEnum.LOGIN_FAIL.getCode());
    }
    public static ResponseResult loginFail(String message){
        return new ResponseResult(ResultEnum.LOGIN_FAIL.getCode(),message);
    }
    public static<T> ResponseResult loginFail(T data){
        return new ResponseResult(ResultEnum.LOGIN_FAIL.getCode(),data);
    }
    public static<T> ResponseResult loginFail(String message, T data){
        return new ResponseResult(ResultEnum.LOGIN_FAIL.getCode(),message,data);
    }

}
