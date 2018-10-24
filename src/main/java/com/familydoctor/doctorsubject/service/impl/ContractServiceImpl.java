package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.bean.ContractBean;
import com.familydoctor.doctorsubject.entity.Contract;
import com.familydoctor.doctorsubject.entity.Member;
import com.familydoctor.doctorsubject.mapper.ContractMapper;
import com.familydoctor.doctorsubject.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;

    /**
     * 添加contract
     *
     * @param contract
     * @return contractMapper.insert(contract) 返回一个int,大于0代表操作成功.
     */
    @Override
    public int insertContract(Contract contract) {
        return contractMapper.insertSelective(contract);
    }

    /**
     * 查询Contract
     *
     * @param id
     * @return contractMapper.selectByPrimaryKey(id) 返回Contract对象
     */
    @Override
    public Contract selectById(String id) {
        return contractMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询一个医生的所有患者
     *
     * @param list
     * @return
     */
    @Override
    public List<Contract> selectByDoctorId(List<String> list) {
        return contractMapper.selectByDoctor(list);
    }

    @Override
    public List<Contract> selectByMemberId(Member member) {
        return selectByMemberId(member);
    }

    @Override
    public List<Contract> selectTrends(ContractBean contractBean) {
        return contractMapper.selectTrends(contractBean);
    }
}
