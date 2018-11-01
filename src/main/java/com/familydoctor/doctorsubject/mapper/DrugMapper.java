package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Drug;

import java.util.List;
import java.util.Map;

public interface DrugMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Drug drug);

    int insert(Drug record);

    int insertSelective(Drug record);

    Drug selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKeyWithBLOBs(Drug record);

    List<Drug> selectAroundDrug(Drug drug);

    int updateByPrimaryKey(Drug record);
}