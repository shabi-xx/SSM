package cn.itsource.dao;

import cn.itsource.entity.User;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class PageTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test() throws Exception{
        PageHelper.startPage(1,2);//当前页 当前页数量
        List<User> users = userMapper.queryPage(1, null);
        users.forEach(e-> System.out.println(e));
    }
}
