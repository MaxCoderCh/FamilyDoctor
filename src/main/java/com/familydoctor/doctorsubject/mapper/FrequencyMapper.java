package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Frequency;

import java.util.List;

public interface FrequencyMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Frequency frequency);

    int insert(Frequency record);

    int insertSelective(Frequency record);

    Frequency selectByPrimaryKey(String id);

    List<Frequency> selectRound();

    List<Frequency> selectPage(Frequency frequency);

    int updateByPrimaryKeySelective(Frequency record);

    int updateByPrimaryKey(Frequency record);
}