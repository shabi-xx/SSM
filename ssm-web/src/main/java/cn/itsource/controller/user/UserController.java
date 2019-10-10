package cn.itsource.controller.user;

import cn.itsource.common.NeedLogin;
import cn.itsource.common.NeedPermission;
import cn.itsource.common.PageCommon;
import cn.itsource.common.ResponseResult;
import cn.itsource.entity.User;
import cn.itsource.service.UserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("user")
@Api(tags = "这是增删改查的")
@Slf4j
@CrossOrigin(allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("listpage/{page}/{name}") ///user/listpage?page=1&name=aa
    @ApiOperation(value = "查询接口")
    @NeedLogin
    @NeedPermission
    public ResponseResult queryPage(
            @PathVariable("page")Integer page,@PathVariable("name") String name){

        if(name.equals("null")){
            name = null;
        }
        PageHelper.startPage(page,3);//当前页 当前页数量
        PageCommon<List<User>> listPageCommon = userService.queryPage(page, name);

        return ResponseResult.success(listPageCommon);
    }

    @PutMapping("user/{id}")
    @ApiOperation(value = "修改用户的接口")
    public ResponseResult updete(
            @PathVariable("id")Integer id, @Validated @RequestBody User user){
        userService.updeteById(user);
        return ResponseResult.success();
    }

    /*@DeleteMapping("user/{id}")
    @ApiOperation(value = "删除一个用户的接口")
    public ResponseResult deleteById(@PathVariable("id")Integer id){
        //校验
        //BindingResultUtil.bindingResult(bindingResult);
        userService.deleteById(id);
        return ResponseResult.success();
    }*/

    @PostMapping("user")
    @ApiOperation(value = "这个是添加接口")
    public ResponseResult add(@RequestBody User user){
        user.setPassword("123456");
        user.setName(user.getUsername());
        userService.addUser(user);
        return ResponseResult.success();
    }

    @DeleteMapping("user/{ids}")
    @ApiOperation(value = "批量删除和普通删除用户的接口")
    public ResponseResult deleteAll(@PathVariable("ids") List<Integer> ids,HttpServletRequest request){
        /*//校验
        BindingResultUtil.bindingResult(bindingResult);*/

        /*获取前台传递的cookie*/
        //stream.of 把数组转为流
        Stream.of(request.getCookies())
                //filter 表示过滤  cookie为每一个元素
                .filter(cookie ->cookie.getName().equals("ssm"))
                .forEach(cookie -> System.out.println("=================="+cookie.getValue()));

        Stream.of(ids).forEach(e-> System.out.println(e));
        userService.deleteUsers(ids);
        return ResponseResult.success();
    }

}
