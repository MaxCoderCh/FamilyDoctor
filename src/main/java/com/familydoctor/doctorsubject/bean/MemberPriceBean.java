package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.MemberPrice;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MemberPriceBean extends MemberPrice {
    private String produceId;

    private String[] orderIds;
}
