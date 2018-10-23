package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Cases;

import java.util.List;


public interface CasesService {

    /**
     * 添加病历信息
     *
     * @param cases
     */
    int addCases(Cases cases);

    /**
     * 更新病历信息
     *
     * @param cases
     */
    int updateCases(Cases cases);

    /**
     * 由会员ID查询对应所有病历
     *
     * @param string
     */
    List<Cases> selectCasesMember(String string);

    /**
     * 由Cases.id查询病历
     *
     * @param string
     */
    Cases selectById(String string);

    /**
     * 分页查询
     *
     * @param cases
     */
    List<Cases> selectPage(Cases cases);

    int delAll();
}
