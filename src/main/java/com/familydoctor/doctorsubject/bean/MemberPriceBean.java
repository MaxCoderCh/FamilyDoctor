package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.MemberPrice;

import lombok.Data;

import java.util.Date;

@Data
public class MemberPriceBean extends MemberPrice {
    private String produceId;

    private String[] orderIds;

    private Date startDate;

    private Date endDate;
}
