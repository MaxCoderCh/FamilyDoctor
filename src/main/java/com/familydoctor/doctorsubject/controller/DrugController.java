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
 * 药品
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
     * @return requestInsertSuccess(drug)
     */
    @PostMapping(value = "add")
    public Map addDrugMsg(Drug drug) {

        if (drug == null) {
            return requestArgumentEmpty("传入参数为空");
        }

        int i = drugService.addDrug(drug);
        if (i > 0) {
            return requestInsertSuccess(drug);
        } else {
            return requestInsertFail("添加药品失败");
        }

    }

    /**
     * 更新药品
     *
     * @param drug
     * @return requestUpdateSuccess(drug)
     */
    @PostMapping(value = "update")
    public Map updateDrugMsg(Drug drug) {

        if (StringUtils.isBlank(drug.getId())) {
            return requestArgumentEmpty("传入参数为空");
        }

        drug.setUpdateTime(addTime());
        drug.setUpdateTime(addTime());
        int i = drugService.updateDrug(drug);
        if (i > 0) {
            return requestUpdateSuccess(drug);
        } else {
            return requestUpdateFail("更新失败");
        }

    }

    /**
     * 删除药品
     *
     * @param drug
     * @return requestDeleteSuccess(drug)
     */
    @GetMapping(value = "softDel")
    public Map deleteDrugMsg(Drug drug) {

        if (StringUtils.isBlank(drug.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        drug.setDeleteTime(addTime());
        int i = drugService.softDel(drug);
        if (i > 0) {
            return requestDeleteSuccess(drug);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 由Id查询药品
     *
     * @param drug
     * @return
     */
    @GetMapping(value = "selectById")
    public Map selectDrugMsgById(Drug drug) {

        if (StringUtils.isBlank(drug.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        Drug resultDrug = drugService.selectDrugById(drug.getId());
        if (resultDrug != null) {
            return requestSelectSuccess(resultDrug);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    //查询药品列表
    @GetMapping(value = "selectAll")
    public Map selectAllDrugMsg(Drug drug) {

        List<Drug> drugList = drugService.selectAllDrug(drug);

        if (drugList != null && !drugList.isEmpty()) {
            return requestSelectSuccess(drugList);
        } else {
            return requestSelectFail("查询失败");
        }
    }

    /**
     * 分页查询
     *
     * @param drug
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(Drug drug) {

        List<Drug> drugList = drugService.selectPage(drug);

        if (drugList != null && !drugList.isEmpty()) {
            return requestSelectSuccess(drugList);
        }

        return requestSelectFail("查询失败");
    }
}
