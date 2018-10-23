package com.familydoctor.doctorsubject.service;


import com.familydoctor.doctorsubject.bean.MemberLableBean;
import com.familydoctor.doctorsubject.entity.MemberLable;

import java.util.List;

public interface MemberLableServce {

    /**
     * 由Member.id查询MemberLable
     *
     * @param memberLable
     */
    MemberLable selectByMember(MemberLable memberLable);

    /**
     * 修改MemberLable
     *
     * @param memberLable
     */
    int updateMemberLable(MemberLable memberLable);

    /**
     * 选择添加MemberLable
     *
     * @param record
     * @return
     */
    int insertSelect(MemberLable record);

    List<MemberLable> selectByList(MemberLableBean memberLableBean);
}
