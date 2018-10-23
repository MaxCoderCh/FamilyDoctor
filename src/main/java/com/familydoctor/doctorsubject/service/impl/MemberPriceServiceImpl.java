package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.MemberPrice;
import com.familydoctor.doctorsubject.mapper.MemberPriceMapper;
import com.familydoctor.doctorsubject.service.MemberPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberPriceServiceImpl implements MemberPriceService {

    @Autowired
    private MemberPriceMapper memberPriceMapper;

    /**
     * 选择添加
     *
     * @param memberPrice
     * @return memberPriceMapper.insertSelective(memberPrice)
     */
    @Override
    public int addMemberPrice(MemberPrice memberPrice) {
        return memberPriceMapper.insertSelective(memberPrice);
    }

    /**
     * 选择查询
     *
     * @param memberPrice
     * @return memberPriceMapper.selectBySelective(memberPrice)
     */
    public MemberPrice selectBySelective(MemberPrice memberPrice) {
        return memberPriceMapper.selectBySelective(memberPrice);
    }
}
