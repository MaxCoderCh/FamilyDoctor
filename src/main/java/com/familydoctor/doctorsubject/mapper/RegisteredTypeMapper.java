package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.RegisteredType;

import java.util.List;

public interface RegisteredTypeMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(RegisteredType registeredType);

    int insert(RegisteredType record);

    int insertSelective(RegisteredType record);

    List<RegisteredType> selectAllRegisteredType();

    RegisteredType selectByPrimaryKey(String id);

    List<RegisteredType> selectByBranch(String id);

    List<RegisteredType> selectPage(RegisteredType registeredType);

    int updateByPrimaryKeySelective(RegisteredType record);

    int updateByPrimaryKey(RegisteredType record);
}