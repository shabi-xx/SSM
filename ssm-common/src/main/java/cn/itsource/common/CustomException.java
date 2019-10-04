package cn.itsource.common;

import lombok.Getter;

import java.io.Serializable;

/**
 * 自定义异常：
 *
 */
@Getter
public class CustomException extends RuntimeException implements Serializable{
    private String message;

    public CustomException(String message){
        super(message);
        this.message = message;
    }

}
