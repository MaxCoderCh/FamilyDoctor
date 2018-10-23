package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Registered;

import java.util.List;

public interface RegisteredMapper {
    int deleteByPrimaryKey(String id);

    List<Registered> selectTrends(Registered registered);

    int insert(Registered record);

    int insertSelective(Registered record);

    Registered selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(Registered record);

    int updateByPrimaryKey(Registered record);
}