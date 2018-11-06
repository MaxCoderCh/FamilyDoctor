package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.bean.ScheduleBean;
import com.familydoctor.doctorsubject.entity.Schedule;

import java.util.List;

public interface ScheduleServce {

    /**
     * 添加备忘
     *
     * @param schedule
     */
    int addSchdule(Schedule schedule);

    /**
     * 删除备忘
     *
     * @param schedule
     */
    int softDel(Schedule schedule);

    /**
     * 物理删除
     *
     * @param id
     */
    int deleteById(String id);

    /**
     * 删除备忘
     *
     * @param record
     */
    int updateById(Schedule record);

    /**
     * 由Id查询备忘
     *
     * @param id
     */
    Schedule selectById(String id);

    /**
     * 查询当天之后所有日程
     *
     * @param schedule
     */
    List<Schedule> selectIntraDayLater(ScheduleBean schedule);

    /**
     * 查询当天开始所有日程
     *
     * @param schedule
     */
    List<Schedule> selectDayAndLate(Schedule schedule);

}
