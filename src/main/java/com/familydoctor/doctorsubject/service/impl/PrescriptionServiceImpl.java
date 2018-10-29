package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.bean.PrescriptionBean;
import com.familydoctor.doctorsubject.entity.Prescription;
import com.familydoctor.doctorsubject.mapper.PrescriptionMapper;
import com.familydoctor.doctorsubject.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    /**
     * 添加处方药品
     *
     * @param prescription
     * @return prescriptionMapper.insert(prescription)
     */
    @Override
    public int addPrescription(Prescription prescription) {
        return prescriptionMapper.insertSelective(prescription);
    }

    /**
     * 删除
     *
     * @param prescription
     * @return prescriptionMapper.softDelete(id)
     */
    @Override
    public int softDel(Prescription prescription) {
        return prescriptionMapper.softDelete(prescription);
    }

    /**
     * 由Cases.id查询处方药品
     *
     * @param prescription
     * @return selectToCases(casesId)
     */
    @Override
    public List<Prescription> selectToCases(Prescription prescription) {
        return prescriptionMapper.selectByCases(prescription);
    }

    /**
     * drigId,prescriptionIdList查询prescription
     *
     * @param prescriptionBean
     */
    @Override
    public List<Prescription> selectByIdListAndDrug(PrescriptionBean prescriptionBean) {
        return prescriptionMapper.selectByIdListAndDrug(prescriptionBean);
    }

    /**
     * id在ids中所有的Prescription
     *
     * @param ids
     */
    @Override
    public List<Prescription> selectByIds(List<String> ids) {
        return prescriptionMapper.selectByIds(ids);
    }

    @Override
    public Prescription selectByPrimaryKey(String id) {
        return prescriptionMapper.selectByPrimaryKey(id);
    }
}
