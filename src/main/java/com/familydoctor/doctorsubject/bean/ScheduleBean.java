package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Schedule;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleBean extends Schedule {
    private Date startDate;  //每天开始时间

    private Date endDate;  //每天结束时间
}
