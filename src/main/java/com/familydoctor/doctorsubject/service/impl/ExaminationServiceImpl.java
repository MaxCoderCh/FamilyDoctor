package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Examination;
import com.familydoctor.doctorsubject.mapper.ExaminationMapper;
import com.familydoctor.doctorsubject.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationMapper examinationMapper;

    @Override
    public int insertSelect(Examination record) {
        return examinationMapper.insertSelective(record);
    }

    @Override
    public Examination selectBySelect(String id) {
        return examinationMapper.selectBySelective(id);
    }
}
