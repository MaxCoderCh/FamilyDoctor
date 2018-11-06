package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.bean.ScheduleBean;
import com.familydoctor.doctorsubject.entity.Schedule;

import java.util.List;

public interface ScheduleMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Schedule schedule);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(String id);

    List<Schedule> selectIntraDayLater(ScheduleBean schedule);

    List<Schedule> selectDayAndLate(Schedule schedule);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);
}