package cn.itsource.service;

import cn.itsource.common.PageCommon;
import cn.itsource.entity.User;

import java.util.List;

public interface UserService {

    User login(String username,String password);

    PageCommon<List<User>> queryPage(Integer page, String name);

    void updeteById(User user);

    void addUser(User user);

    void deleteById(Integer id);

    void deleteUsers(List<Integer> ids);

}
