package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.bean.MemberLableBean;
import com.familydoctor.doctorsubject.entity.MemberLable;

import java.util.List;

public interface MemberLableMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberLable record);

    int insertSelective(MemberLable record);

    MemberLable selectByPrimaryKey(String id);

    MemberLable selectByMemberId(String id);

    List<MemberLable> selectByList(MemberLableBean memberLableBean);

    int updateByPrimaryKeySelective(MemberLable record);

    int updateByPrimaryKey(MemberLable record);
}