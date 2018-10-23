package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.CasesDisease;

import java.util.List;

public interface CasesDiseaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(CasesDisease record);

    int insertSelective(CasesDisease record);

    CasesDisease selectByPrimaryKey(String id);

    List<CasesDisease> selectPage(CasesDisease casesDisease);

    int updateByPrimaryKeySelective(CasesDisease record);

    int updateByPrimaryKey(CasesDisease record);
}