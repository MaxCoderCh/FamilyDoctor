package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Contract;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ContractBean extends Contract {

    private Date startDateL;

    private Date endDateL;

}
