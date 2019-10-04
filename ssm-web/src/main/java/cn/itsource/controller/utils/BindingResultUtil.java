package cn.itsource.controller.utils;

import cn.itsource.common.CustomException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

//校验工具
public class BindingResultUtil {

    public static void bindingResult(BindingResult bindingResult) {

        //1.判断校验是否错误
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            //2.获取到错误信息
            //stream 转为流   map可以做任何操作
            String collect = allErrors.stream().map(error -> error.getDefaultMessage())
                    //collect 可以转map 可以转list 可以转set 可以转字符串
                    .collect(Collectors.joining(","));
            //3.把错误抛出去
            throw new CustomException(collect);
        }
    }
}
