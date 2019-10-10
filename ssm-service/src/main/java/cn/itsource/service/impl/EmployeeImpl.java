package cn.itsource.service.impl;

import cn.itsource.FastJsonTools;
import cn.itsource.GetCodeUtil;
import cn.itsource.RedisUtils;
import cn.itsource.common.Constant;
import cn.itsource.common.ResponseResult;
import cn.itsource.dao.EmployeeMapper;
import cn.itsource.entity.Employee;
import cn.itsource.param.LoginParam;
import cn.itsource.param.PhoneLoginParam;
import cn.itsource.param.PhoneParam;
import cn.itsource.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
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

    @Override
    public ResponseResult selectByPhone(PhoneParam phone) {

        Employee employee = employeeMapper.selectByPhone(phone.getPhone());
        //如果通过手机没有找到这个用户
        if(employee==null){
            return ResponseResult.fail(Constant.Phone.NO_PHONE);
        }else {
            //生成一个 随机数
            String uuid = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
            log.info(uuid+"-----------");
            //发送短息
           // GetCodeUtil.sendCode(phone.getPhone(), uuid);
            //把验证码放在 redis中
            Jedis jedis = RedisUtils.getJedis();
            jedis.set(Constant.Phone.LOGIN_CODE_KEY+employee.getPhone(),uuid);
            jedis.expire(employee.getUsername(), 60*10);
            RedisUtils.closeJedis(jedis);

            return ResponseResult.success(Constant.Phone.SEND_CODE);
        }
    }

    @Override
    public ResponseResult selectByPhoneCode(PhoneLoginParam phoneLoginParam) {
        Employee employee = employeeMapper.selectByPhone(phoneLoginParam.getPhone());
        if(employee==null){
            return ResponseResult.fail(Constant.Phone.NO_PHONE);
        }
        //从数据库拿到验证码
        Jedis jedis = RedisUtils.getJedis();
        String code = jedis.get(Constant.Phone.LOGIN_CODE_KEY +employee.getPhone());

        if(code==null){
            return ResponseResult.fail(Constant.Phone.NO_SEND_CODE);
        }
        if(!code.equals(phoneLoginParam.getVcode().toUpperCase())){
            return ResponseResult.fail(Constant.Phone.CODE_ERROR);
        }

        //2.把登录用户放进数据库 redis
        //获取链接
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
