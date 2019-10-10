package cn.itsource.dao;

import cn.itsource.entity.Employee;

import java.util.List;
import cn.itsource.param.PhoneParam;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    Employee selectByUsername(@Param("username")String username);

    List<String> selectUrlById(Long id);

    Employee selectByPhone(String phone);
}