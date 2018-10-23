package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Branch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BranchMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(String id);

    List<Branch> selectAllBranchs();

    List<Branch> selectByHospitalId(Branch branch);

    int insert(Branch record);

    int insertSelective(Branch record);

    Branch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);
}