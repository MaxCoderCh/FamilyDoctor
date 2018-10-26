package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.bean.MemberPriceBean;
import com.familydoctor.doctorsubject.entity.MemberPrice;

import java.util.List;

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
     * @param memberPriceBean
     */
    List<MemberPrice> selectTrends(MemberPriceBean memberPriceBean);

    List<MemberPrice> selectBySelective(MemberPrice memberPrice);

    int updateByPrimaryKeySelective(MemberPrice record);

    List<MemberPrice> selectThreePram(MemberPriceBean memberPriceBean);

    /**
     * memberIdList查询memberPriceList
     *
     * @param memberIdList
     */
    List<String> selectPriceByMemberIdList(List<String> memberIdList);

}
