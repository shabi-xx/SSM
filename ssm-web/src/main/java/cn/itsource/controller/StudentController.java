package cn.itsource.controller;

import cn.itsource.common.ResponseResult;
import cn.itsource.param.StudentQueryParam;
import cn.itsource.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("queryAll")
    public ResponseResult queryAll(){
        return ResponseResult.success(studentService.queryAll());
    }

    @PostMapping("queryAll2")
    public ResponseResult queryAll2(@RequestBody StudentQueryParam studentQueryParam){
        log.info("获得前台传递的数据："+studentQueryParam);
        return ResponseResult.success(studentService.queryAll());
    }
}
