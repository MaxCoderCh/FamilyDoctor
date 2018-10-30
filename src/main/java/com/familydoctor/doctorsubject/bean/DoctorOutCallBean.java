package com.familydoctor.doctorsubject.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DoctorOutCallBean {

    private String yearSize;        //年费次数

    private String SingleSize;        //普通次数

    private String outSize;            //出诊次数

    private String subsidy;            //补贴

    private String totalPrice;        //总费用

    private String memberTotal;    //总年费

    private String diagnosisTotal; //总普通费用

    private List<Map<String, String>> diagnosisListMap;
}
