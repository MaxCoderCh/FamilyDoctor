package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Auxiliary;

public interface AuxiliaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Auxiliary record);

    int insertSelective(Auxiliary record);

    Auxiliary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Auxiliary record);

    int updateByPrimaryKey(Auxiliary record);
}