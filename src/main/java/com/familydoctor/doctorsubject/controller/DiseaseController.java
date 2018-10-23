package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.Disease;
import com.familydoctor.doctorsubject.service.DiseaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 疾病信息,返回值为成功返回值
 */
@RestController
@RequestMapping(value = "familydoctor/disease")
public class DiseaseController extends BaseController {

    @Autowired
    private DiseaseService diseaseService;

    /**
     * 添加疾病信息
     *
     * @param disease
     * @return requestInsertSuccess(disease)
     */
    @PostMapping(value = "add")
    public Map addDiseaseMsg(Disease disease) {

        if (disease == null) {
            return requestArgumentEmpty("传入值为空");
        }

        int i = diseaseService.addDisease(disease);
        if (i > 0) {
            return requestInsertSuccess(disease);
        } else {
            return requestSelectFail("添加失败");
        }
    }

    /**
     * 修改疾病信息
     *
     * @param disease
     * @return requestUpdateSuccess(disease)
     */
    @PostMapping(value = "update")
    public Map updateDiseaseMsg(Disease disease) {

        if (StringUtils.isBlank(disease.getId())) {
            return requestArgumentEmpty("传入参数为空");
        }

        disease.setUpdateTime(addTime());
        int i = diseaseService.updateDisease(disease);
        if (i > 0) {
            return requestUpdateSuccess(disease);
        } else {
            return requestUpdateFail("更新失败");
        }
    }

    /**
     * 删除疾病信息
     *
     * @param disease
     * @return requestDeleteSuccess(disease)
     */
    @GetMapping(value = "softDelete")
    public Map deleteDiseaseMsg(Disease disease) {

        if (StringUtils.isBlank(disease.getId())) {
            return requestArgumentEmpty("传入参数为空");
        }

        disease.setDeleteTime(addTime());
        int i = diseaseService.softDel(disease);
        if (i > 0) {
            return requestDeleteSuccess(disease);
        } else {
            return requestDeleteFail(disease);
        }

    }

    /**
     * 查询所有疾病列表
     *
     * @return requestSelectSuccess(diseaseList)
     */
    @GetMapping(value = "selectAll")
    public Map selectAllDisease() {

        List<Disease> diseaseList = diseaseService.selectAll();
        if (diseaseList != null && !diseaseList.isEmpty()) {
            return requestSelectSuccess(diseaseList);
        } else {
            return requestSelectFail("查询所有疾病列表失败");
        }
    }

    /**
     * 分页查询
     *
     * @param disease
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(Disease disease) {

        List<Disease> diseaseList = diseaseService.selectPage(disease);

        if (diseaseList != null && !diseaseList.isEmpty()) {
            return requestSelectSuccess(disease);
        }

        return requestSelectFail("查询失败");
    }
}
