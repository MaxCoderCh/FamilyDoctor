package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.MemberPrice;

import com.familydoctor.doctorsubject.entity.PriceType;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class MemberPriceBean extends MemberPrice {
    private String produceId; //会员产品Id

    private String[] orderIds; //缴费类型 按照price_type - id填写对应的id

    private String produceType;

    private String producePrice;

    private Date startDate;

    private Date endDate;
}
