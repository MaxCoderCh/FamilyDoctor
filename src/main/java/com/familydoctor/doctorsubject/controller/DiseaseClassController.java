package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.DiseaseClass;
import com.familydoctor.doctorsubject.service.DiseaseClassService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 疾病所属类别(familydoctor/diseaseclass)
 * 添加(add),删除(softdelete),修改(update),查询所有(selectall),分页查询(selectpage)
 */
@RestController
@RequestMapping(value = "familydoctor/diseaseclass")
public class DiseaseClassController extends BaseController {

    @Autowired
    private DiseaseClassService diseaseClassService;

    /**
     * 添加疾病类别
     *
     * @param diseaseClass
     */
    @PostMapping(value = "add")
    public Map addDiseaseClass(DiseaseClass diseaseClass) {

        if (diseaseClass == null) {
            return requestArgumentEmpty("传入对象为空");
        }

        diseaseClass.setCreateUser(getCurrentUser());
        int i = diseaseClassService.insertDiseaseClsass(diseaseClass);
        if (i > 0) {
            return requestInsertSuccess(diseaseClass);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 删除疾病类别
     *
     * @param diseaseClass
     */
    @GetMapping(value = "softdelete")
    public Map deleteDiseaseClass(DiseaseClass diseaseClass) {

        if (StringUtils.isBlank(diseaseClass.getId())) {
            return requestArgumentEmpty("传入值为空");
        }

        diseaseClass.setDeleteTime(addTime());
        int i = diseaseClassService.softDel(diseaseClass);
        if (i > 0) {
            return requestDeleteSuccess(diseaseClass);
        } else {
            return requestDeleteFail("删除失败");
        }
    }

    /**
     * 修改DiseaseClass
     *
     * @param diseaseClass
     */
    @PostMapping(value = "update")
    public Map updateDiseaseClass(DiseaseClass diseaseClass) {

        if (StringUtils.isBlank(diseaseClass.getId())) {
            return requestArgumentEmpty("传入值为空");
        }

        diseaseClass.setUpdateTime(addTime());
        int i = diseaseClassService.updateById(diseaseClass);
        if (i > 0) {
            return requestUpdateSuccess(diseaseClass);
        } else {
            return requestUpdateFail("更新失败");
        }

    }

    /**
     * 查询所有疾病
     */
    @GetMapping(value = "selectall")
    public Map selectAllDiseaseClass() {

        List<DiseaseClass> diseaseClassList = diseaseClassService.selectAll();
        if (diseaseClassList != null && !diseaseClassList.isEmpty()) {
            return requestSelectSuccess(diseaseClassList);
        } else {
            return requestSelectFail("查询所有疾病列表失败");
        }
    }

    /**
     * 分页查询
     *
     * @param diseaseClass
     */
    @GetMapping(value = "selectpage")
    public Map selectPage(DiseaseClass diseaseClass) {

        List<DiseaseClass> diseaseClassList = diseaseClassService.selectPage(diseaseClass);

        if (diseaseClassList != null && !diseaseClassList.isEmpty()) {
            return requestSelectSuccess(diseaseClassList);
        }

        return requestSelectFail("查询失败");
    }
}

