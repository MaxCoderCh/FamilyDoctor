package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Member;

import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Member member);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String id);

    Member selectByMemberCard(String id);

    List<Member> selectRound();

    List<Member> selectPage(Member member);


    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}