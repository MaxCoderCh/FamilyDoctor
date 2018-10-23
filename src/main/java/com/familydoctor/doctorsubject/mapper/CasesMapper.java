package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Cases;

import java.util.List;

public interface CasesMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(String id);

    int insert(Cases record);

    int insertSelective(Cases record);

    Cases selectByPrimaryKey(String id);

    int deleteAll();

    List<Cases> selectCasesByMember(String string);

    List<Cases> selectPage(Cases cases);

    int updateByPrimaryKeySelective(Cases record);

    int updateByPrimaryKey(Cases record);
}