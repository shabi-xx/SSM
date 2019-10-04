package cn.itsource.controller;

import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.controller.param.SwaggerTest;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("swagger")
@Api(tags="swagger测试的controller")       //@Api：用在类上，说明该类的作用
@Slf4j
public class SwaggerTestController {

    @ApiOperation(value = "对用户信息的查询")   //@ApiOperation：用在方法上，说明方法的作用
    @GetMapping("queryUser")
    public ResponseResult queryUser(
            /*@ApiParam:用在参数上*/
            @ApiParam(name = "username",required = true,example = "zhangsan") String username){
        return ResponseResult.success();
    }

    @ApiOperation(value = "对用户信息的查询")   //@ApiOperation：用在方法上，说明方法的作用
    @GetMapping("queryUserById")
    @ApiImplicitParams({    //@ApiImplicitParams：用在方法上包含一组参数说明
        @ApiImplicitParam(name = "username",example = "zhangsan",required = true,defaultValue = "lisi"),
        @ApiImplicitParam(name = "id",example = "1",required = true,defaultValue = "0")
    })
    @ApiResponses({     //@ApiResponses：用于表示一组响应
            /*@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息*/
            @ApiResponse(code = Constant.SUSSESS_CODE,message = "请求成功"),
            @ApiResponse(code = 002,message = "请求失败")
    })
    /*@RequestParam才能起到真正的限制作用*/
    public ResponseResult queryUserById(@RequestParam(required = true,defaultValue = "lisi",name="username") String username, Integer id){
        log.info("获得的参数："+username);
        return ResponseResult.success();
    }

    @ApiOperation(value = "增加用户",notes = "增加用户的接口")
    @PostMapping("insert")
    public ResponseResult insert(
       @ApiParam(name="swaggerTest",value = "接受SwaggerTest类型json参数")
                /*接收 json  参数*/
               @RequestBody SwaggerTest swaggerTest
   ){
        log.info("接受到的参数为："+swaggerTest);
        return ResponseResult.success();
    }
}
