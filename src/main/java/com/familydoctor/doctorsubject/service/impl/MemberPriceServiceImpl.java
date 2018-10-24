package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.bean.MemberPriceBean;
import com.familydoctor.doctorsubject.entity.MemberPrice;
import com.familydoctor.doctorsubject.mapper.MemberPriceMapper;
import com.familydoctor.doctorsubject.service.MemberPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public MemberPrice selectBySelective(MemberPrice memberPrice) {
        return memberPriceMapper.selectBySelective(memberPrice);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberPrice record) {
        return memberPriceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MemberPrice> selectThreePram(MemberPriceBean memberPriceBean) {
        return memberPriceMapper.selectThreePram(memberPriceBean);
    }

    @Override
    public List<MemberPrice> selectTwoPram(MemberPrice memberPrice) {
        return memberPriceMapper.selectTwoPram(memberPrice);
    }
}
