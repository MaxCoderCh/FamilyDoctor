package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.bean.PrescriptionBean;
import com.familydoctor.doctorsubject.entity.Prescription;
import com.familydoctor.doctorsubject.entity.Schedule;

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
     */
    List<Prescription> selectToCases(Prescription prescription);

    /**
     * drigId,prescriptionIdList查询prescription
     *
     * @param prescriptionBean
     */
    List<Prescription> selectByIdListAndDrug(PrescriptionBean prescriptionBean);

    /**
     * id在ids中所有的Prescription
     *
     * @param ids
     */
    List<Prescription> selectByIds(List<String> ids);

    Prescription selectByPrimaryKey(String id);
}
