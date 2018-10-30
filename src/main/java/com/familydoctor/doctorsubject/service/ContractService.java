package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.bean.ContractBean;
import com.familydoctor.doctorsubject.entity.Contract;
import com.familydoctor.doctorsubject.entity.Member;

import java.util.List;

public interface ContractService {

    /**
     * 添加Contract
     *
     * @param Contract
     */
    int insertContract(Contract Contract);

    /**
     * 查询Contract
     *
     * @param id
     */
    Contract selectById(String id);

    /**
     * 由医生Id查询其所患者
     *
     * @param contract
     */
    List<Contract> selectParm(Contract contract);

    List<Contract> selectByMemberId(Member member);

    List<Contract> selectTrends(ContractBean contractBean);

    /**
     * memberIdList查询Contract
     *
     * @param memberIdList
     */
    List<Contract> selectByMemberList(List<String> memberIdList);
}
