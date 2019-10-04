package cn.itsource.service.impl;

import cn.itsource.dao.SSMMapper;
import cn.itsource.entity.SSM;
import cn.itsource.service.SSMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SSMServiceImpl implements SSMService {
    @Autowired
    private SSMMapper ssmMapper;
    @Override
    public List<SSM> queryAll() {
        /*if(1==1){//某种条件
            throw new CustomException("出现异常了..");
        }*/
        return ssmMapper.queryAll();
    }

    @Override
    @Transactional//增删改require  查询support
    public void insert(SSM ssm) {
        ssmMapper.insert(ssm);
        int i=1/0;
    }
}
