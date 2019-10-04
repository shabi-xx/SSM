package cn.itsource.dao;

import cn.itsource.entity.SSM;

import java.util.List;

public interface SSMMapper {
    List<SSM> queryAll();

    void insert(SSM ssm);
}
