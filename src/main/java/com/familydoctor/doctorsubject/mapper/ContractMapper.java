package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.bean.ContractBean;
import com.familydoctor.doctorsubject.entity.Contract;
import com.familydoctor.doctorsubject.entity.Member;

import java.util.List;

public interface ContractMapper {
    int deleteByPrimaryKey(String id);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String id);

    List<Contract> selectByDoctor(List<String> list);

    List<Contract> selectTrends(ContractBean contractBean);

    List<Contract> selectByMemberId(Member member);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}