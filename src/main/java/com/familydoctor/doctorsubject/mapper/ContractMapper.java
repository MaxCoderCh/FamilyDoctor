package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.bean.ContractBean;
import com.familydoctor.doctorsubject.entity.Contract;
import com.familydoctor.doctorsubject.entity.Member;
import sun.awt.geom.AreaOp;

import java.util.List;

public interface ContractMapper {
    int deleteByPrimaryKey(String id);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String id);

    List<Contract> selectParm(Contract contract);

    List<Contract> selectTrends(ContractBean contractBean);

    List<Contract> selectByMemberId(Member member);

    List<Contract> selectByMemberList(List<String> memberIdList);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}