package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Prescription;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionBean extends Prescription {

    private List<String> prescriptionIds;
}
