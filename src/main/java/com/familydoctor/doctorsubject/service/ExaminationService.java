package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Examination;

public interface ExaminationService {

    int insertSelect(Examination record);

    Examination selectBySelect(String id);
}
