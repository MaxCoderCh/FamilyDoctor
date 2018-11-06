package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Branch;
import com.familydoctor.doctorsubject.mapper.BranchMapper;

import com.familydoctor.doctorsubject.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YoonaLT
 * @version 1.0
 * Running version:1.8
 * Functional Description: Implements BranchService
 */
@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchMapper branchMapper;

    /**
     * 实现doctorsubject.service.BranchService接口,添加科室
     *
     * @param branch
     * @return branchMapper.insert(branch)
     */
    @Override
    public int addBranch(Branch branch) {
        return branchMapper.insertSelective(branch);
    }

    /**
     * 实现doctorsubject.service.BranchService接口,由Branch.id查询科室信息
     *
     * @param id
     * @return branchMapper.selectByPrimaryKey(id)
     */
    @Override
    public Branch selectBranch(String id) {
        return branchMapper.selectByPrimaryKey(id);
    }

    /**
     * 实现doctorsubject.service.BranchService接口,由Branch.id更新科室信息
     *
     * @param branch
     * @return branchMapper.updateByPrimaryKey(branch)
     */
    @Override
    public int updateBranch(Branch branch) {
        return branchMapper.updateByPrimaryKeySelective(branch);
    }

    /**
     * 实现doctorsubject.service.BranchService接口,由Branch.id删除科室信息
     *
     * @param id
     * @return branchMapper.deleteByPrimaryKey(id)
     */
    @Override
    public int softDel(String id) {
        return branchMapper.softDelete(id);
    }

    /**
     * 实现doctorsubject.service.BranchService接口,查询所有科室信息
     *
     * @param
     */
    @Override
    public List<Branch> selectAllBranch() {
        return branchMapper.selectAllBranchs();
    }

    /**
     * 物理删除
     *
     * @param id
     * @return branchMapper.deleteByPrimaryKey(id)
     */
    @Override
    public int deleteById(String id) {
        return branchMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询同一医院内所有的Branch
     *
     * @param branch
     * @return branchMapper.selectByHospitalId(branch)
     */
    @Override
    public List<Branch> selectByHospitalId(Branch branch) {
        return branchMapper.selectByHospitalId(branch);
    }

}
