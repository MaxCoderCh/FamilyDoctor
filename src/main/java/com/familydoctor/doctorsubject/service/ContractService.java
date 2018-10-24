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
     * 由医生Id查询其所患者(contractId - produceId - doctorId - List<produceID> - List<contract>)
     *
     * @param list
     */
    List<Contract> selectByDoctorId(List<String> list);

    List<Contract> selectByMemberId(Member member);

    List<Contract> selectTrends(ContractBean contractBean);
}
