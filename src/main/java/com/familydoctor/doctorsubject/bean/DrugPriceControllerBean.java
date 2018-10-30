package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Drug;
import com.familydoctor.doctorsubject.entity.DrugPrice;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DrugPriceControllerBean extends DrugPrice {

    private Drug drug; //药品对象
}

