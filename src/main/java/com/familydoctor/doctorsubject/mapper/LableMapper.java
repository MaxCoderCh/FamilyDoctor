package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Lable;

import java.util.List;

public interface LableMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Lable lable);

    int insert(Lable record);

    int insertSelective(Lable record);

    Lable selectByPrimaryKey(String id);

    List<Lable> selectRound();

    List<Lable> selectPage(Lable lable);

    List<Lable> selectByName(String[] lableName);

    int updateByPrimaryKeySelective(Lable record);

    int updateByPrimaryKey(Lable record);
}