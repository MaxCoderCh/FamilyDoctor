package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Member;
import com.familydoctor.doctorsubject.entity.MemberPrice;

public interface MemberPriceMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberPrice record);

    int insertSelective(MemberPrice record);

    MemberPrice selectByPrimaryKey(String id);

    MemberPrice selectBySelective(MemberPrice memberPrice);

    int updateByPrimaryKeySelective(MemberPrice record);

    int updateByPrimaryKey(MemberPrice record);
}