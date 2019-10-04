package cn.itsource.controller;

import cn.itsource.common.ResponseResult;
import cn.itsource.entity.SSM;
import cn.itsource.service.SSMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Stream;

@RestController//controller+responseBody
@RequestMapping("ssm")
//@CrossOrigin(origins = "*",allowCredentials="true")
@Slf4j
public class SSMController {

    @GetMapping("ssm")
    public String ssm(String name, HttpServletRequest request){
        log.info("获得前台的名字："+name);
        //Stream.of:把数组转换为流对象
        //filter:表示过滤
        Stream.of(request.getCookies())
                //cookie代表数组中每一个元素   filter过滤
                .filter(cookie->cookie.getName().equals("ssm"))
                .forEach(cookie -> System.out.println(cookie.getValue()));

       /* for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("ssm")){
                System.out.println(cookie.getValue());
            }
        }*/
        return "hello ssm";
    }
    @Autowired
    private SSMService ssmService;

    @RequestMapping("queryAll")
    public ResponseResult queryAll(){
        return ResponseResult.success(ssmService.queryAll());
    }
    @PostMapping("insert")
    public ResponseResult insert(SSM ssm) {
        ssmService.insert(ssm);
        return ResponseResult.success();
    }

    @GetMapping("test1")
    public ResponseResult test1(Date date){
        log.info("获得date："+date);
        Date date1 = new Date();
        log.info("11111---"+date1);
        return ResponseResult.success();
    }
}
