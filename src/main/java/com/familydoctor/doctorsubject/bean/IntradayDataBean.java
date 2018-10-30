package com.familydoctor.doctorsubject.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class IntradayDataBean {
    private String id;

    private String drugPrice;//出药钱数

    private String diagnosisPrice;//出诊钱数

    private String memberSize;  //签约人数

    private String totalPrice; //今日总收益

}
