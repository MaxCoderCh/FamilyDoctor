package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.bean.ScheduleBean;
import com.familydoctor.doctorsubject.entity.Schedule;
import com.familydoctor.doctorsubject.mapper.ScheduleMapper;
import com.familydoctor.doctorsubject.service.ScheduleServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServceImpl implements ScheduleServce {

    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * 添加备注
     *
     * @param schedule
     */
    @Override
    public int addSchdule(Schedule schedule) {
        return scheduleMapper.insertSelective(schedule);
    }

    /**
     * 删除备忘
     *
     * @param schedule
     */
    @Override
    public int softDel(Schedule schedule) {
        return scheduleMapper.softDelete(schedule);
    }

    /**
     * 物理删除
     *
     * @param id
     * @return scheduleMapper.deleteByPrimaryKey(id)
     */
    @Override
    public int deleteById(String id) {
        return scheduleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新备忘
     *
     * @param record
     */
    @Override
    public int updateById(Schedule record) {
        return scheduleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 由ID查询备忘
     *
     * @param id
     */
    @Override
    public Schedule selectById(String id) {
        return scheduleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询当天之后所有日程
     *
     * @param schedule
     */
    @Override
    public List<Schedule> selectIntraDayLater(ScheduleBean schedule) {
        return scheduleMapper.selectIntraDayLater(schedule);
    }

    /**
     * 查询当天开始所有日程
     *
     * @param schedule
     */
    @Override
    public List<Schedule> selectDayAndLate(Schedule schedule) {
        return scheduleMapper.selectDayAndLate(schedule);
    }
}
