package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.Schedule;

public interface ScheduleMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(Schedule schedule);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);
}