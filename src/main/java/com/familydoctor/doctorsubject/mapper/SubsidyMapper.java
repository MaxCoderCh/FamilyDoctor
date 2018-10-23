package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Subsidy;

public interface SubsidyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Subsidy record);

    int insertSelective(Subsidy record);

    Subsidy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Subsidy record);

    int updateByPrimaryKey(Subsidy record);
}