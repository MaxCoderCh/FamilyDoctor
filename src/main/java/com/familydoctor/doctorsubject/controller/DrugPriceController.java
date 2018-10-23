package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.YoonaLTsUtils.DateUtils;
import com.familydoctor.doctorsubject.entity.Drug;
import com.familydoctor.doctorsubject.entity.DrugPrice;
import com.familydoctor.doctorsubject.service.DrugPriceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 药品价格
 */
@RestController
@RequestMapping(value = "familydoctor/drugPrice")
public class DrugPriceController extends BaseController {

    @Autowired
    private DrugPriceService drugPriceService;

    /**
     * 添加药品价格
     *
     * @param drugPrice
     * @return
     */
    @PostMapping(value = "add")
    public Map addDrugPrice(DrugPrice drugPrice) {

        if (drugPrice == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = drugPriceService.addDrugPrice(drugPrice);
        if (i > 0) {
            return requestInsertSuccess(drugPrice);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 删除药品价格
     *
     * @param drugPrice
     * @return requestDeleteSuccess(drugPrice)
     */
    @GetMapping(value = "softDel")
    public Map deleteDrugPrice(DrugPrice drugPrice) {

        if (StringUtils.isBlank(drugPrice.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        drugPrice.setDeleteTime(addTime());
        int i = drugPriceService.softDel(drugPrice);
        if (i > 0) {
            return requestDeleteSuccess(drugPrice);
        } else {
            return requestDeleteFail("添加失败");
        }

    }

    /**
     * 更新未使用药品价格
     *
     * @param drugPrice
     * @return requestUpdateSuccess(drugPrice)
     */
    @PostMapping(value = "update")
    public Map updateDrugPrice(DrugPrice drugPrice) {

        if (drugPrice == null) {
            return requestArgumentEmpty("参数为空");
        }

        drugPrice.setUpdateTime(addTime());
        int i = drugPriceService.updateDp(drugPrice);
        if (i > 0) {
            return requestUpdateSuccess(drugPrice);
        } else {
            return requestUpdateFail("更新失败");
        }

    }

    /**
     * 由ID查询对应的药品价格
     *
     * @param drugPrice
     * @return
     */
    @GetMapping(value = "selectById")
    public Map selectById(DrugPrice drugPrice) {

        if (StringUtils.isBlank(drugPrice.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        DrugPrice resultDrugPrice = drugPriceService.selectById(drugPrice.getId());
        if (resultDrugPrice != null) {
            return requestSelectSuccess(resultDrugPrice);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 查询所有药品价格
     *
     * @return requestSelectSuccess(drugPriceList)
     */
    @GetMapping(value = "selectAll")
    public Map selectAllDrugPrice() {

        List<DrugPrice> drugPriceList = drugPriceService.selectAllPrice();
        if (drugPriceList != null && !drugPriceList.isEmpty()) {
            return requestSelectSuccess(drugPriceList);
        } else {
            return requestSelectFail("查询失败");
        }
    }

    /**
     * 重新设置已经使用的药品价格
     *
     * @param drugPriceId
     * @param startTime1
     * @param stopTime1
     * @return
     */
    @PostMapping(value = "updateUse")
    public Map reinstallDrugPrice(String drugPriceId, String startTime1, String stopTime1) {

        Date stTime = DateUtils.stringToDate(startTime1);
        Date spTime = DateUtils.stringToDate(stopTime1);

        //获取药品Id
        DrugPrice drugPrice = drugPriceService.selectById(drugPriceId);
        String drugId = drugPrice.getDrugId();

        //删除DrugPrice.Id对应旧的药品价格
        drugPrice.setDeleteTime(addTime());
        drugPriceService.softDel(drugPrice);

        //重置药品价格
        DrugPrice newDrugPrice = new DrugPrice();
        newDrugPrice.setDrugId(drugId);
        newDrugPrice.setCreateTime(addTime());
        newDrugPrice.setCreateUser(getCurrentUser());
        newDrugPrice.setStopTime(stTime);
        newDrugPrice.setStartTime(spTime);

        int i = drugPriceService.addDrugPrice(newDrugPrice);
        if (i > 0) {
            return requestInsertSuccess("重置药品价格成功");
        }
        return requestInsertFail("重置失败");
    }

    /**
     * 分页查询
     *
     * @param drugPrice
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(DrugPrice drugPrice) {

        List<DrugPrice> drugPriceList = drugPriceService.selectPage(drugPrice);

        if (drugPriceList != null && !drugPriceList.isEmpty()) {
            return requestSelectSuccess(drugPriceList);
        }

        return requestSelectFail("查询失败");
    }
}
