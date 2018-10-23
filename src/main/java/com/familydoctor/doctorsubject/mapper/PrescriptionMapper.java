package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Prescription;

import java.util.List;

public interface PrescriptionMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Prescription prescription);

    int insert(Prescription record);

    int insertSelective(Prescription record);

    Prescription selectByPrimaryKey(String id);

    List<Prescription> selectByCases(Prescription prescription);

    int updateByPrimaryKeySelective(Prescription record);

    int updateByPrimaryKey(Prescription record);
}