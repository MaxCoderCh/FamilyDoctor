package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Drug;

import java.util.List;

public interface DrugService {

    /**
     * 添加药品
     *
     * @param drug
     */
    int addDrug(Drug drug);

    /**
     * 删除药品
     *
     * @param drug
     */
    int softDel(Drug drug);

    /**
     * 修改药品
     *
     * @param drug
     */
    int updateDrug(Drug drug);

    /**
     * 根据Id查询对应的药品
     *
     * @param id
     */
    Drug selectDrugById(String id);

    /**
     * 查询药品列表
     */
    List<Drug> selectAllDrug(Drug drug);

    List<Drug> selectPage(Drug drug);
}
