package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.bean.MemberPriceBean;
import com.familydoctor.doctorsubject.entity.Member;
import com.familydoctor.doctorsubject.entity.MemberPrice;

import java.util.List;

public interface MemberPriceMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberPrice record);

    int insertSelective(MemberPrice record);

    MemberPrice selectByPrimaryKey(String id);

    MemberPrice selectBySelective(MemberPrice memberPrice);

    List<MemberPrice> selectThreePram(MemberPriceBean memberPriceBean);

    List<MemberPrice> selectTwoPram(MemberPrice memberPrice);

    int updateByPrimaryKeySelective(MemberPrice record);

    int updateByPrimaryKey(MemberPrice record);
}