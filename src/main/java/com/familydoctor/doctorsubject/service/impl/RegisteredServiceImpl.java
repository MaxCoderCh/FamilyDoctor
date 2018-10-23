package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Registered;
import com.familydoctor.doctorsubject.mapper.RegisteredMapper;
import com.familydoctor.doctorsubject.service.RegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredServiceImpl implements RegisteredService {

    @Autowired
    private RegisteredMapper registeredMapper;

    /**
     * 添加挂号信息
     *
     * @param record
     * @return
     */
    @Override
    public int insertRegistered(Registered record) {
        return registeredMapper.insertSelective(record);
    }

    /**
     * Id查询挂号信息
     *
     * @param id
     * @return registeredMapper.selectByPrimaryKey(id)
     */
    @Override
    public Registered selectById(String id) {
        return registeredMapper.selectByPrimaryKey(id);
    }

    /**
     * 动态查询
     *
     * @param Registered
     * @return
     */
    @Override
    public List<Registered> selectParam(Registered Registered) {
        return registeredMapper.selectTrends(Registered);
    }
}
