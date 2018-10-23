package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Registered;
import lombok.Data;

@Data
public class RegisteredBean extends Registered {

    private String startTime;
    private String endTime;
}
