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
 * 疾病所属类别,返回值为成功返回值
 */
@RestController
@RequestMapping(value = "familydoctor/diseaseClass")
public class DiseaseClassController extends BaseController {

    @Autowired
    private DiseaseClassService diseaseClassService;

    /**
     * 添加疾病类别
     *
     * @param diseaseClass
     * @return requestInsertSuccess(diseaseClass)
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
     * @return requestDeleteSuccess(diseaseClass)
     */
    @GetMapping(value = "softDelete")
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
     * @return requestUpdateSuccess(diseaseClass)
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
     *
     * @return requestSelectSuccess(diseaseClassList), 结果存储在List列表
     */
    @GetMapping(value = "selectAll")
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
     * @param
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(DiseaseClass diseaseClass) {

        List<DiseaseClass> diseaseClassList = diseaseClassService.selectPage(diseaseClass);

        if (diseaseClassList != null && !diseaseClassList.isEmpty()) {
            return requestSelectSuccess(diseaseClass);
        }

        return requestSelectFail("查询失败");
    }
}

