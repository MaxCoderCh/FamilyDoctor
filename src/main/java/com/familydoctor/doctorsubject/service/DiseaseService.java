package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Disease;

import java.util.List;

public interface DiseaseService {

    /**
     * 添加疾病信息
     *
     * @param disease
     */
    int addDisease(Disease disease);

    /**
     * 修改疾病信息
     *
     * @param disease
     */
    int updateDisease(Disease disease);

    /**
     * 删除纪斌发信息
     *
     * @param disease
     */
    int softDel(Disease disease);

    /**
     * 查询所有疾病列表
     */
    List<Disease> selectAll();

    List<Disease> selectPage(Disease disease);
}
