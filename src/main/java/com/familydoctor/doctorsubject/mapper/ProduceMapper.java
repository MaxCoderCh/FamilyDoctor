package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Produce;

import java.util.List;

public interface ProduceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Produce record);

    int softDelete(Produce produce);

    int insertSelective(Produce record);

    Produce selectByPrimaryKey(String id);

    List<Produce> selectTrends(Produce produce);

    List<Produce> selectByDoctor(String id);

    List<String> selectByDoctorIdGetId(String id);

    int updateByPrimaryKeySelective(Produce record);

    int updateByPrimaryKey(Produce record);
}