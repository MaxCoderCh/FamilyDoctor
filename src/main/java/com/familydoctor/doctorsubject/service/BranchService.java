package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Branch;

import java.util.List;

/**
 * @author YoonaLT
 * @version 1.0
 * Running version:1.8
 * Functional Description: BranchService
 */

public interface BranchService {

    /**
     * 为医院添加科室分支
     *
     * @param branch
     */
    int addBranch(Branch branch);

    /**
     * 由Branch.id查询科室信息
     *
     * @param id
     */
    Branch selectBranch(String id);

    /**
     * 由Branch.id更新科室信息
     *
     * @param branch
     */
    int updateBranch(Branch branch);

    /**
     * 由Branch.id删除对应的科室
     *
     * @param id
     */
    int softDel(String id);

    /**
     * 物理删除
     *
     * @param id
     */
    int deleteById(String id);

    /**
     * 按条件查询医院所有科室
     *
     * @param
     */
    List<Branch> selectAllBranch();

    /**
     * 查询同一医院内所有Branch
     * @return
     */
    List<Branch> selectByHospitalId(Branch branch);
}
