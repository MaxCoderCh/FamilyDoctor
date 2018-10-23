package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Drug;
import com.familydoctor.doctorsubject.entity.DrugPrice;
import lombok.Data;

@Data
public class DrugPriceControllerBean extends DrugPrice {

    private Drug drug; //药品对象
}

