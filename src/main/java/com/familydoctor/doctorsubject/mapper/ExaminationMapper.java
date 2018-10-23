package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Examination;

public interface ExaminationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Examination record);

    int insertSelective(Examination record);

    Examination selectByPrimaryKey(String id);

    Examination selectBySelective(String id);

    int updateByPrimaryKeySelective(Examination record);

    int updateByPrimaryKey(Examination record);
}