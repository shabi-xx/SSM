package cn.itsource.service.impl;

import cn.itsource.common.Constant;
import cn.itsource.common.CustomException;
import cn.itsource.Md5Util;
import cn.itsource.common.PageCommon;
import cn.itsource.dao.UserMapper;
import cn.itsource.entity.User;
import cn.itsource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    //登录
    @Override
    public User login(String username, String password) {
        User login = userMapper.login(username, password);
        if(login==null){
            throw new CustomException(Constant.User.LOGIN_ERROR);
        }
        return login;
    }
    //查询
    @Override
    public PageCommon<List<User>> queryPage(Integer page, String name) {

        List<User> users = userMapper.queryPage(page, name);
        return new PageCommon<>(123, users);


    }
    //修改
    @Override
    public void updeteById( User user) {
        userMapper.updeteById(user);
    }

    @Override
    public void addUser(User user) {
        String password = user.getPassword();
        String code = Md5Util.getCode(password);
        user.setPassword(code);
        userMapper.addUser(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void deleteUsers(List<Integer> ids) {
        userMapper.deleteUsers(ids);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
