package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.entity.Schedule;
import com.familydoctor.doctorsubject.service.ScheduleServce;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 备忘
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
     * @return requestInsertSuccess(schedule)
     */
    @PostMapping(value = "add")
    public Map addSchduleMsg(Schedule schedule) {

        if (schedule == null) {
            return requestArgumentEmpty("paramIsEmpty");
        }

        int i = scheduleServce.addSchdule(schedule);
        if (i > 0) {
            return requestInsertSuccess(schedule);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 删除备忘
     *
     * @param schedule
     * @return requestDeleteSuccess(schedule)
     */
    @GetMapping(value = "softDel")
    public Map softDelSchedule(Schedule schedule) {

        if (StringUtils.isBlank(schedule.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        schedule.setDeleteTime(addTime());
        int i = scheduleServce.softDel(schedule);
        if (i > 0) {
            return requestDeleteSuccess(schedule);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 物理删除备忘
     *
     * @param schedule
     * @return requestDeleteSuccess(schedule)
     */
    @GetMapping(value = "delete")
    public Map DelSchedule(Schedule schedule) {

        if (StringUtils.isBlank(schedule.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        schedule.setDeleteTime(addTime());
        int i = scheduleServce.deleteById(schedule.getId());
        if (i > 0) {
            return requestDeleteSuccess(schedule);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 修改备忘
     *
     * @param schedule
     * @return requestUpdateSuccess(schedule)
     */
    @PostMapping(value = "update")
    public Map updateHospitalMsg(Schedule schedule) {

        if (StringUtils.isBlank(schedule.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        schedule.setUpdateTime(addTime());
        int i = scheduleServce.updateById(schedule);
        if (i > 0) {
            return requestUpdateSuccess(schedule);
        } else {
            return requestUpdateFail("修改失败");
        }

    }

    /**
     * 查询备忘
     *
     * @param schedule
     * @return requestSelectSuccess(resultSchedule)
     */
    @GetMapping(value = "selectById")
    public Map selectScheduleById(Schedule schedule) {

        if (StringUtils.isBlank(schedule.getId())) {
            return requestArgumentEmpty("paramISEmpty");
        }

        Schedule resultSchedule = scheduleServce.selectById(schedule.getId());
        if (resultSchedule != null) {
            return requestSelectSuccess(resultSchedule);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    //查询当天日程

    //查询当天及以后日程   分页

}

