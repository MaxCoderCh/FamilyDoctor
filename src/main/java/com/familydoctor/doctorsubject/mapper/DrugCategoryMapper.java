package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.DrugCategory;

import java.util.List;

public interface DrugCategoryMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(DrugCategory drugCategory);

    int insert(DrugCategory record);

    int insertSelective(DrugCategory record);

    DrugCategory selectByPrimaryKey(String id);

    List<DrugCategory> selectAll();

    int updateByPrimaryKeySelective(DrugCategory record);

    List<DrugCategory> selectPage(DrugCategory drugCategory);

    int updateByPrimaryKey(DrugCategory record);
}