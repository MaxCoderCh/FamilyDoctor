package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Frequency;

import java.util.List;

public interface FrequencyService {

    /**
     * 物理删除给药
     *
     * @param frequency
     */
    int deleteById(Frequency frequency);

    /**
     * 逻辑删除给药
     *
     * @param frequency
     */
    int softDel(Frequency frequency);

    /**
     * 添加给药
     *
     * @param record
     */
    int addFrequency(Frequency record);

    /**
     * 由Id查询对应给药
     *
     * @param frequency
     */
    Frequency selectById(Frequency frequency);

    /**
     * 更新给药
     *
     * @param frequency
     */
    int updateById(Frequency frequency);

    /**
     * 查询所有给药
     *
     * @return
     */
    List<Frequency> selectAll();

    List<Frequency> selectPage(Frequency frequency);

}
