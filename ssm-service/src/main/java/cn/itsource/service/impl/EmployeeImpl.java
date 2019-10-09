package cn.itsource.service.impl;

import cn.itsource.FastJsonTools;
import cn.itsource.RedisUtils;
import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.dao.EmployeeMapper;
import cn.itsource.entity.Employee;
import cn.itsource.param.LoginParam;
import cn.itsource.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ResponseResult login(LoginParam loginParam) {
        Employee employee =  employeeMapper.selectByUsername(loginParam.getUsername());
        if(employee==null){//登录失败
            return  ResponseResult.fail(Constant.User.USERNAME_ERROR);
        }
        if(!employee.getPassword().equals(loginParam.getPassword())){
            return ResponseResult.fail(Constant.User.PASSWORD_ERROR);
        }


        //2.把登录用户放进数据库 redis
        //获取链接
        Jedis jedis = RedisUtils.getJedis();
        //把 key 和 value 字符串放进去
        jedis.set(employee.getUsername(), FastJsonTools.toJSONString(employee));

        //3.还需要查询权限出来
        List<String> urls = employeeMapper.selectUrlById(employee.getId());
        //把权限路径放进 redis数据库中
        jedis.set(Constant.Permission.PERMISSION_KEY+employee.getUsername(),FastJsonTools.toJSONString(urls));

        //redis key过期时间
        jedis.expire(employee.getUsername(), 60*30);
        //关闭资源
        RedisUtils.closeJedis(jedis);
        return ResponseResult.success(employee);
    }
}
