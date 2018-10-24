package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Produce;

import java.util.List;

public interface ProduceService {

    /**
     * 医生套餐添加
     *
     * @param record
     */
    int insertByDoctor(Produce record);

    /**
     * 查询医生套餐列表
     *
     * @param id
     */
    List<Produce> selectByDoctorId(String id);

    /**
     * 获取医生套餐列表的Id
     *
     * @param id
     * @return
     */
    List<String> selectByDoctorToId(String id);

    /**
     * Id查询套餐
     *
     * @param id
     */
    Produce selectById(String id);

    /**
     * 动态查询
     *
     * @param produce
     */
    List<Produce> selectTrends(Produce produce);

    int softDel(Produce produce);

    int updateById(Produce record);
}
