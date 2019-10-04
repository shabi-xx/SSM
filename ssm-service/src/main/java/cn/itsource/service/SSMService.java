package cn.itsource.service;

import cn.itsource.entity.SSM;

import java.util.List;

public interface SSMService {
    List<SSM> queryAll();

    void insert(SSM ssm);
}
