package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.CheckCode;

public interface CheckCodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(CheckCode record);

    int insertSelective(CheckCode record);

    CheckCode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CheckCode record);

    int updateByPrimaryKey(CheckCode record);
}