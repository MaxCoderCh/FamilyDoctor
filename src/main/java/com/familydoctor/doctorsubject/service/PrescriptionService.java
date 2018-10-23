package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Prescription;

import java.util.List;

public interface PrescriptionService {

    /**
     * 添加处方药品
     *
     * @param prescription
     */
    int addPrescription(Prescription prescription);

    /**
     * 删除 Prescription
     *
     * @param prescription
     */
    int softDel(Prescription prescription);

    /**
     * 查询所属疾病
     *
     * @param prescription
     * @return
     */
    List<Prescription> selectToCases(Prescription prescription);
}
