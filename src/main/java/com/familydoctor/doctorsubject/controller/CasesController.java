package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.Cases;
import com.familydoctor.doctorsubject.service.CasesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.familydoctor.doctorsubject.YoonaLTsUtils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 病历相关,返回值均为请求成功返回值
 */
@Slf4j
@RestController
@RequestMapping(value = "familydoctor/cases")
public class CasesController extends BaseController {

    @Autowired
    private CasesService casesService;

    /**
     * 添加病历
     *
     * @param cases
     * @return requestInsertSuccess(cases)
     */
    @PostMapping(value = "add")
    public Map addCasesMassage(Cases cases) {

        if (cases == null) {
            return requestArgumentError("添加内容为空");
        }

        cases.setCreateDoctor(getCurrentUser());
        int i = casesService.addCases(cases);
        if (i > 0) {
            return requestInsertSuccess(cases);
        } else {
            return requestInsertFail("添加失败");
        }
    }

    /**
     * 修改病历
     *
     * @param cases
     * @return return requestUpdateSuccess(cases)
     */
    @PostMapping(value = "update")
    public Map updateCasesMassage(Cases cases) {

        Date date = casesService.selectById(cases.getId()).getCreateTime();
        if (cases == null) {
            return requestArgumentEmpty("传入修改对象为空");
        } else if (DateUtils.timeOutTest(date) < 1) {
            return requestUpdateFail("时间超出,不可修改!");
        }

        cases.setUpdateTime(addTime());
        int i = casesService.updateCases(cases);
        if (i > 0) {
            return requestUpdateSuccess(cases);
        } else {
            return requestArgumentError("修改失败");
        }

    }

    /**
     * 由MemberId查询其诊疗所有病历列表
     *
     * @param cases
     * @return requestSelectSuccess(casesList)
     */
    @GetMapping(value = "selectByMemberId")
    public Map selectCasesByMemberId(Cases cases) {

        if (StringUtils.isBlank(cases.getMemberId())) {
            return requestArgumentEmpty("Member.id为空");
        }

        List<Cases> casesList = casesService.selectCasesMember(cases.getMemberId());
        if (casesList != null && !casesList.isEmpty()) {
            return requestSelectSuccess(casesList);
        } else {
            return requestSelectSuccess("查询结果为空");
        }

    }

    /**
     * 根据病历ID查询对应的病历
     *
     * @param cases
     * @return requestSelectSuccess(cases)
     */
    @GetMapping(value = "selectById")
    public Map selectCasesById(Cases cases) {

        if (StringUtils.isBlank(cases.getId())) {
            return requestArgumentEmpty(cases.getId());
        }

        Cases resultCases = casesService.selectById(cases.getId());
        if (resultCases != null) {
            return requestSelectSuccess(resultCases);
        } else {
            return requestSelectFail("查询结果为空");
        }
    }

    /**
     * 分页查询
     *
     * @param cases
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(Cases cases) {

        List<Cases> casesList = casesService.selectPage(cases);

        if (casesList != null && !casesList.isEmpty()) {
            return requestSelectSuccess(casesList);
        }

        return requestSelectFail("查询失败");
    }

    @GetMapping(value = "deleteAll")
    public void delAllCases() {
        casesService.delAll();
    }
}
