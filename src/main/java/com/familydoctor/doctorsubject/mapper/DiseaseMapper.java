package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Disease;

import java.util.List;

public interface DiseaseMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Disease disease);

    int insert(Disease record);

    int insertSelective(Disease record);

    Disease selectByPrimaryKey(String id);

    List<Disease> selectRound();

    List<Disease> selectPage(Disease disease);

    int updateByPrimaryKeySelective(Disease record);

    int updateByPrimaryKey(Disease record);
}