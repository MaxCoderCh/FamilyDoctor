package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Hospital;

import java.util.List;

public interface HospitalMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Hospital hospital);

    int insert(Hospital record);

    int insertSelective(Hospital record);

    Hospital selectByPrimaryKey(String id);

    List<Hospital> selectRound(String pageNo, String pageSize);

    int updateByPrimaryKeySelective(Hospital record);

    int updateByPrimaryKey(Hospital record);
}