package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Contract;
import lombok.Data;

import java.util.Date;

@Data
public class ContractBean extends Contract {

    private Date startDateL;

    private Date endDateL;

}
