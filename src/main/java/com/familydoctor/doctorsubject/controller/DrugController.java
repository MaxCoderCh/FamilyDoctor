package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.Drug;
import com.familydoctor.doctorsubject.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 药品(familydoctor/drug)
 * 添加(add),修改(update),删除(softdelete),id查询(selectbyid),查询所有(selectall),分页查询(selectpage)
 */
@Slf4j
@RestController
@RequestMapping(value = "familydoctor/drug")
public class DrugController extends BaseController {

    @Autowired
    private DrugService drugService;

    /**
     * 添加药品
     *
     * @param drug
     */
    @PostMapping(value = "add")
    public Map addDrugMsg(Drug drug) {

        if (drug == null) {
            return requestArgumentEmpty("传入参数为空");
        }

        int i = drugService.addDrug(drug);
        if (i > 0) {
            return requestInsertSuccess("添加成功");
        }
        return requestInsertFail("添加药品失败");
    }


    /**
     * 更新药品
     *
     * @param drug
     */
    @PostMapping(value = "update")
    public Map updateDrugMsg(Drug drug) {

        if (StringUtils.isBlank(drug.getId())) {
            return requestArgumentEmpty("传入参数为空");
        }

        drug.setUpdateTime(addTime());
        int i = drugService.updateDrug(drug);
        if (i > 0) {
            return requestUpdateSuccess("更新成功");
        }
        return requestUpdateFail("更新失败");
    }

    /**
     * 删除药品
     *
     * @param drug
     */
    @GetMapping(value = "softdelete")
    public Map deleteDrugMsg(Drug drug) {

        if (StringUtils.isBlank(drug.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        drug.setDeleteTime(addTime());
        int i = drugService.softDel(drug);
        if (i > 0) {
            return requestDeleteSuccess("delete_succeed");
        }
        return requestDeleteFail("删除失败");
    }


    /**
     * 由Id查询药品
     *
     * @param drug
     */
    @GetMapping(value = "selectbyid")
    public Map selectDrugMsgById(Drug drug) {

        if (StringUtils.isBlank(drug.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        Drug resultDrug = drugService.selectDrugById(drug.getId());
        if (resultDrug != null) {
            return requestSelectSuccess(resultDrug);
        }
        return requestSelectFail("查询失败");
    }

    /**
     * 查询所有药品
     *
     * @param drug
     */
    @GetMapping(value = "selectall")
    public Map selectAllDrugMsg(Drug drug) {

        List<Drug> drugList = drugService.selectAllDrug(drug);

        if (drugList != null && !drugList.isEmpty()) {
            return requestSelectSuccess(drugList);
        }
        return requestSelectFail("查询失败");
    }

}
