package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.MemberPrice;

public interface MemberPriceService {

    /**
     * 添加MemberPrice
     *
     * @param memberPrice
     */
    int addMemberPrice(MemberPrice memberPrice);

    /**
     * 选择查询
     *
     * @param memberPrice
     */
    MemberPrice selectBySelective(MemberPrice memberPrice);
}
