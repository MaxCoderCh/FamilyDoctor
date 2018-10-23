package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Contract;

import java.util.List;

public interface ContractMapper {
    int deleteByPrimaryKey(String id);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String id);

    List<Contract> selectByDoctor(List<String> list);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}