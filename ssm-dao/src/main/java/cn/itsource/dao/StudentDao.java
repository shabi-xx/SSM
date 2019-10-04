package cn.itsource.dao;

import cn.itsource.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> queryAll();
}
