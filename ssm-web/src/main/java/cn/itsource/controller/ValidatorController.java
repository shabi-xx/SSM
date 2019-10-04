package cn.itsource.controller;

import cn.itsource.common.CustomException;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.param.ValidatorTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("validator")
@Slf4j
@Api(tags="校验测试的controller")
public class ValidatorController {//

    @ApiOperation(value = "校验的测试方法哈")
    @PostMapping("test")
    public ResponseResult test(
            //BindingResult 包装出现的错误  @Validated 开启验证  @RequestBody 接收json参数
            @Validated @RequestBody ValidatorTest validatorTest, BindingResult bindingResult){

        //1.判断校验是否错误
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            //2.获取到错误信息
            //stream 转为流   map可以做任何操作
            String collect = allErrors.stream().map(error -> error.getDefaultMessage())
                    //collect 可以转map 可以转list 可以转set 可以转字符串
                    .collect(Collectors.joining(","));
            //3.把错误抛出去
            throw new CustomException(collect);
        }
        return ResponseResult.success();
    }
}
