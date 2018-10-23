package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Usages;

public interface UsagesMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Usages usages);

    int insert(Usages record);

    int insertSelective(Usages record);

    Usages selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Usages record);

    int updateByPrimaryKey(Usages record);
}