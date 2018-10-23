package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Registered;

import java.util.List;

public interface RegisteredService {

    /**
     * 添加挂号信息
     *
     * @param record
     */
    int insertRegistered(Registered record);

    /**
     * 由Id获取挂号信息
     *
     * @param id
     */
    Registered selectById(String id);

    /**
     * 动态查询
     *
     * @param registered
     */
    List<Registered> selectParam(Registered registered);

}
