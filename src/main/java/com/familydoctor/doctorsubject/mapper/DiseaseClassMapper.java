package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.DiseaseClass;

import java.util.List;

public interface DiseaseClassMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(DiseaseClass diseaseClass);

    int insert(DiseaseClass record);

    int insertSelective(DiseaseClass record);

    DiseaseClass selectByPrimaryKey(String id);

    List<DiseaseClass> selectRound();

    List<DiseaseClass> selectPage(DiseaseClass diseaseClass);

    int updateByPrimaryKeySelective(DiseaseClass record);

    int updateByPrimaryKey(DiseaseClass record);
}