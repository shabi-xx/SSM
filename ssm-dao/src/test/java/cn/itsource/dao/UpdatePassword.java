package cn.itsource.dao;

import cn.itsource.entity.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UpdatePassword {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        //查询到所有的用户
        List<User> users = userMapper.queryPage(11, "");
        users.forEach(e->{
            //获取到用户名
            String username = e.getUsername();
            //加密密码
            SimpleHash password = new SimpleHash("md5", username, "hzw", 10);
            //密码放进对象中
            e.setPassword(password.toString());
            userMapper.updeteAllPassword(e);
        });
    }
}
