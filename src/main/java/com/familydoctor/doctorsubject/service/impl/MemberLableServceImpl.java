package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.bean.MemberLableBean;
import com.familydoctor.doctorsubject.entity.MemberLable;
import com.familydoctor.doctorsubject.mapper.MemberLableMapper;
import com.familydoctor.doctorsubject.service.MemberLableServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class MemberLableServceImpl implements MemberLableServce {

    @Autowired
    private MemberLableMapper memberLableMapper;

    /**
     * 由Member.Id查询MemberLable
     *
     * @param memberLable
     * @return memberLableMapper.selectByMemberId(memberLable.getId ())
     */
    @Override
    public MemberLable selectByMember(MemberLable memberLable) {
        return memberLableMapper.selectByMemberId(memberLable);
    }

    /**
     * 修改MemberLable
     *
     * @param memberLable
     * @return memberLableMapper.updateByPrimaryKey(memberLable)
     */
    @Override
    public int updateMemberLable(MemberLable memberLable) {
        return memberLableMapper.updateByPrimaryKeySelective(memberLable);
    }

    /**
     * 添加MemberLable
     *
     * @param record
     * @return memberLableMapper.insertSelective(record)
     */
    @Override
    public int insertSelect(MemberLable record) {
        return memberLableMapper.insertSelective(record);
    }

    @Override
    public List<MemberLable> selectByList(MemberLableBean memberLableBean) {
        return memberLableMapper.selectByList(memberLableBean);
    }
}
