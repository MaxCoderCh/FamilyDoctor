package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.entity.Examination;
import com.familydoctor.doctorsubject.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;


/**
 * 体检接口(familydoctor/examination)
 * 添加(add)
 */
@RestController
@RequestMapping(value = "familydoctor/examination")
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    //添加体检信息
    @PostMapping(value = "add")
    public int insertExamination(Examination examination) {
        return examinationService.insertSelect(examination);
    }

    //查询体检信息
    @GetMapping(value = "select")
    public Examination selectExamination(Examination examination) {
        return examinationService.selectBySelect(examination.getMemberId());
    }
}
