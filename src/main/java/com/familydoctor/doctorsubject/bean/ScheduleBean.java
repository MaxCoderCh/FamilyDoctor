package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Schedule;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleBean extends Schedule {
    private Date startDate;

    private Date endDate;
}
