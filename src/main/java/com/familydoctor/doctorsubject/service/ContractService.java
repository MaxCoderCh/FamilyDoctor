package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Contract;

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
}