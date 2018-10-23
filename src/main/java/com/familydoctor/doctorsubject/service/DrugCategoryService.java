package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.DrugCategory;

import java.util.List;

/**
 * 药品类别
 */
public interface DrugCategoryService {

    /**
     * 添加药品类别
     *
     * @param drugCategory
     */
    int addDrugCategory(DrugCategory drugCategory);

    /**
     * 修改药品类别
     *
     * @param drugCategory
     */
    int updateDrugCategory(DrugCategory drugCategory);

    /**
     * 删除药品类别
     *
     * @param drugCategory
     */
    int softDel(DrugCategory drugCategory);

    /**
     * 由Id查询药品类别信息
     *
     * @param drugCategory
     */
    DrugCategory selectDrugCategory(DrugCategory drugCategory);

    /**
     * 查询所有药品类别信息
     *
     */
    List<DrugCategory> selectAllMsg();

    List<DrugCategory> selectPage(DrugCategory drugCategory);
}
