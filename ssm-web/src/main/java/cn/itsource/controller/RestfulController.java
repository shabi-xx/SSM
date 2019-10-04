package cn.itsource.controller;

import cn.itsource.common.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restful")
public class RestfulController {

    @GetMapping("ssm/{id}")
    // restful风格  地址栏都是一致的  只是请求方式的不一样
    //   restful/ssm/id
    public ResponseResult get(@PathVariable("id")Integer id){
        return ResponseResult.success("get方法:"+id);
    }
    @PostMapping("ssm/{id}")
    public ResponseResult post(@PathVariable("id")Integer id){
        return ResponseResult.success("post方法:"+id);
    }
    @PutMapping("ssm/{id}")
    public ResponseResult put(@PathVariable("id")Integer id){
        return ResponseResult.success("put方法:"+id);
    }
}

