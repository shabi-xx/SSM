package cn.itsource.dao;

import cn.itsource.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User login(@Param("username") String username,@Param("password") String password);

    List<User> queryPage(@Param("page")Integer page,@Param("name") String name);

    void updeteById( User user);

    void addUser(User user);

    void deleteById(Integer id);

    void deleteUsers( List<Integer> ids);

    void updeteAllPassword(User user);

    User findByUsername(String username);

}
