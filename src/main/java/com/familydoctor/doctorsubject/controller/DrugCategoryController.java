package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.DrugCategory;
import com.familydoctor.doctorsubject.service.DrugCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 药品类别(familydoctor/drugcategory)
 * 添加(add),修改(update),删除(softdelete),id查询(selectbyid),查询所有(selectall),分页查询(selectpage)
 */
@RestController
@RequestMapping(value = "familydoctor/drugcategory")
public class DrugCategoryController extends BaseController {

    @Autowired
    private DrugCategoryService drugCategoryService;

    /**
     * 添加药品类别
     *
     * @param drugCategory
     */
    @PostMapping(value = "add")
    public Map addDrugCategoryMsg(DrugCategory drugCategory) {

        if (drugCategory == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = drugCategoryService.addDrugCategory(drugCategory);
        if (i > 0) {
            return requestInsertSuccess(drugCategory);
        }
        return requestInsertFail("添加失败");

    }

    /**
     * 修改药品类别信息
     *
     * @param drugCategory
     */
    @PostMapping(value = "update")
    public Map updateDrugCategoryMsg(DrugCategory drugCategory) {

        if (StringUtils.isBlank(drugCategory.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        drugCategory.setUpdateTime(addTime());
        int i = drugCategoryService.updateDrugCategory(drugCategory);
        if (i > 0) {
            return requestUpdateSuccess("修改成功");
        }
        return requestUpdateFail("修改失败");

    }

    /**
     * 删除药品类别
     *
     * @param drugCategory
     */
    @GetMapping(value = "softdelete")
    public Map deleteDrugCategoryMsg(DrugCategory drugCategory) {

        if (StringUtils.isBlank(drugCategory.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        drugCategory.setDeleteTime(addTime());
        int i = drugCategoryService.softDel(drugCategory);
        if (i > 0) {
            return requestDeleteSuccess("删除成功");
        }
        return requestDeleteFail("删除失败");

    }

    /**
     * 由Id查询所属药品类别信息
     *
     * @param drugCategory
     */
    @GetMapping(value = "selectbyid")
    public Map selectDrugCategoryById(DrugCategory drugCategory) {
        if (StringUtils.isBlank(drugCategory.getId())) {
            return requestArgumentEmpty("传入参数为空");
        }

        DrugCategory resultDrugCategory = drugCategoryService.selectDrugCategory(drugCategory);
        if (resultDrugCategory != null) {
            return requestSelectSuccess(resultDrugCategory);
        }
        return requestSelectFail("查询结果为空");
    }

    /**
     * 查询所有药品类别
     *
     * @return drugCategoryList
     */
    @GetMapping(value = "selectall")
    public Map selectAllDrugCategory() {

        List<DrugCategory> drugCategoryList = drugCategoryService.selectAllMsg();
        if (drugCategoryList != null && !drugCategoryList.isEmpty()) {
            return requestSelectSuccess(drugCategoryList);
        }

        return requestSelectFail("查询结果失败");

    }

    /**
     * 分页查询
     *
     * @param drugCategory
     */
    @GetMapping(value = "selectpage")
    public Map selectPage(DrugCategory drugCategory) {

        List<DrugCategory> drugCategoryList = drugCategoryService.selectPage(drugCategory);

        if (drugCategoryList != null && !drugCategoryList.isEmpty()) {
            return requestSelectSuccess(drugCategoryList);
        }

        return requestSelectFail("查询失败");
    }

}
