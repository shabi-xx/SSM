package cn.itsource.service.impl;

import cn.itsource.dao.StudentDao;
import cn.itsource.entity.Student;
import cn.itsource.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<Student> queryAll() {
        return studentDao.queryAll();
    }
}
