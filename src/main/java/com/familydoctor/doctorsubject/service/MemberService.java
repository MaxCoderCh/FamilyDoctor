package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {

    /**
     * 添加Member
     *
     * @param record
     */
    int add(Member record);

    /**
     * 删除Member
     *
     * @param member
     */
    int softDel(Member member);

    /**
     * 由Id查询对应的Member
     *
     * @param member
     */
    Member selectById(Member member);

    /**
     * 由身份证号查询Member
     *
     * @param member
     */
    Member selectByCard(Member member);

    /**
     * 修改Member
     *
     * @param record
     */
    int updateById(Member record);

    /**
     * 查询Member列表
     *
     * @return
     */
    List<Member> selectAll();

    List<Member> selectPage(Member member);
}
