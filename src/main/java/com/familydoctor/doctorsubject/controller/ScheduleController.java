package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.YoonaLTsUtils.DateUtils;
import com.familydoctor.doctorsubject.bean.ScheduleBean;
import com.familydoctor.doctorsubject.entity.Schedule;
import com.familydoctor.doctorsubject.service.ScheduleServce;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 备忘(amilydoctor/schedule)
 * 添加(add),删除(softdelete),修改(update),通过id查询(selectbuid),当日以后(daylater),当日及以后(dayandlater)
 */
@RestController
@RequestMapping(value = "familydoctor/schedule")
public class ScheduleController extends BaseController {

    @Autowired
    private ScheduleServce scheduleServce;

    /**
     * 添加备忘信息
     *
     * @param schedule
     */
    @PostMapping(value = "add")
    public Map addSchduleMsg(Schedule schedule) {

        if (schedule == null) {
            return requestArgumentEmpty("paramIsEmpty");
        }

        Date time = new Date();
        if (schedule.getScheduleTime().getTime() < time.getTime()) {
            return requestUnsuccessful("日程时间不能小于当前时间");
        }

        int i = scheduleServce.addSchdule(schedule);
        if (i > 0) {
            return requestInsertSuccess("添加成功");
        }
        return requestInsertFail("添加失败");
    }


    /**
     * 删除备忘
     *
     * @param schedule
     */
    @GetMapping(value = "softdelete")
    public Map softDelSchedule(Schedule schedule) {

        if (StringUtils.isBlank(schedule.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        schedule.setDeleteTime(addTime());
        int i = scheduleServce.softDel(schedule);
        if (i > 0) {
            return requestDeleteSuccess("删除成功");
        }
        return requestDeleteFail("删除失败");
    }


    /**
     * 物理删除备忘
     *
     * @param schedule
     */
    @GetMapping(value = "delete")
    public Map DelSchedule(Schedule schedule) {

        if (StringUtils.isBlank(schedule.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        schedule.setDeleteTime(addTime());
        int i = scheduleServce.deleteById(schedule.getId());
        if (i > 0) {
            return requestDeleteSuccess("删除成功");
        }
        return requestDeleteFail("删除失败");
    }


    /**
     * 修改备忘
     *
     * @param schedule
     */
    @PostMapping(value = "update")
    public Map updateHospitalMsg(Schedule schedule) {

        if (StringUtils.isBlank(schedule.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        schedule.setUpdateTime(addTime());
        int i = scheduleServce.updateById(schedule);
        if (i > 0) {
            return requestUpdateSuccess("成功");
        }
        return requestUpdateFail("修改失败");
    }


    /**
     * 查询备忘(单条)
     *
     * @param schedule
     */
    @GetMapping(value = "selectbyid")
    public Map selectScheduleById(Schedule schedule) {

        if (StringUtils.isBlank(schedule.getId())) {
            return requestArgumentEmpty("paramISEmpty");
        }

        Schedule resultSchedule = scheduleServce.selectById(schedule.getId());
        if (resultSchedule != null) {
            return requestSelectSuccess(resultSchedule);
        }
        return requestSelectFail("查询失败");
    }


    /**
     * 查询当天以后日程,分页
     */
    @GetMapping(value = "daylater")
    public Map intraDayLater(Schedule schedule) {

        Date startDate = DateUtils.intradayBegin();
        Date endDate = DateUtils.intradayEnd();
        ScheduleBean scheduleBean = new ScheduleBean();
        scheduleBean.setStartDate(startDate);
        scheduleBean.setEndDate(endDate);
        scheduleBean.setCreateDoctor(getCurrentUser());
        scheduleBean.setPageNo(schedule.getPageNo());
        scheduleBean.setPageSize(schedule.getPageSize());

        List<Schedule> scheduleList = scheduleServce.selectIntraDayLater(scheduleBean);
        if (scheduleList != null && !scheduleList.isEmpty()) {
            return requestSelectSuccess(scheduleList);
        }
        return requestSelectFail("查询失败");
    }

    /**
     * 查询当天及以后日程  分页
     *
     * @param schedule
     */
    @GetMapping(value = "dayandlater")
    public Map intraDayAndLater(Schedule schedule) {

        Date date = DateUtils.intradayBegin();
        schedule.setCreateTime(date);
        schedule.setCreateDoctor(getCurrentUser());

        List<Schedule> scheduleList = scheduleServce.selectDayAndLate(schedule);
        if (scheduleList != null && !scheduleList.isEmpty()) {
            return requestSelectFail("查询失败");
        }
        return requestSelectSuccess(scheduleList);
    }


}

