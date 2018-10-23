package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Member;
import com.familydoctor.doctorsubject.mapper.MemberMapper;
import com.familydoctor.doctorsubject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 添加会员信息
     *
     * @param record
     * @return memberMapper.insert(record)
     */
    @Override
    public int add(Member record) {
        return memberMapper.insertSelective(record);
    }

    /**
     * 删除Member
     *
     * @param member
     * @return memberMapper.softDelete(member.getId ())
     */
    @Override
    public int softDel(Member member) {
        return memberMapper.softDelete(member);
    }

    /**
     * 由Id查询对应的Member
     *
     * @param member
     * @return memberMapper.selectByPrimaryKey(member.getId ())
     */
    @Override
    public Member selectById(Member member) {
        return memberMapper.selectByPrimaryKey(member.getId());
    }

    /**
     * 由身份证查询member
     *
     * @param member
     * @return memberMapper.selectByMemberCard(member.getMemberCard ())
     */
    @Override
    public Member selectByCard(Member member) {
        return memberMapper.selectByMemberCard(member.getMemberCard());
    }

    /**
     * 更新Member
     *
     * @param record
     * @return memberMapper.updateByPrimaryKey(record)
     */
    @Override
    public int updateById(Member record) {
        return memberMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询列表Member
     *
     * @return memberMapper.selectRound()
     */
    @Override
    public List<Member> selectAll() {
        return memberMapper.selectRound();
    }


    @Override
    public List<Member> selectPage(Member member) {
        return memberMapper.selectPage(member);
    }
}
