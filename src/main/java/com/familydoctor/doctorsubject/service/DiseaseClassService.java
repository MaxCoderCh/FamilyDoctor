package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.DiseaseClass;

import java.util.List;

/**
 * 疾病所属类别
 */
public interface DiseaseClassService {

    /**
     * 添加疾病类别信息
     *
     * @param diseaseClass
     */
    int insertDiseaseClsass(DiseaseClass diseaseClass);

    /**
     * 修改疾病类别信息
     *
     * @param diseaseClass
     */
    int updateById(DiseaseClass diseaseClass);

    /**
     * 删除疾病类别信息
     *
     * @param diseaseClass
     */
    int softDel(DiseaseClass diseaseClass);

    /**
     * 查询所有疾病类别
     */
    List<DiseaseClass> selectAll();

    /**
     * 分页查询
     *
     * @param diseaseClass
     */
    List<DiseaseClass> selectPage(DiseaseClass diseaseClass);
}
