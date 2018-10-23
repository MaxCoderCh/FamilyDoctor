package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Lable;
import com.familydoctor.doctorsubject.entity.LableClass;

import java.util.List;

public interface LableClassMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(LableClass lableClass);

    int insert(LableClass record);

    int insertSelective(LableClass record);

    LableClass selectByPrimaryKey(String id);

    List<LableClass> selectRound();

    List<LableClass> selectPage(LableClass lableClass);

    int updateByPrimaryKeySelective(LableClass record);

    int updateByPrimaryKey(LableClass record);
}