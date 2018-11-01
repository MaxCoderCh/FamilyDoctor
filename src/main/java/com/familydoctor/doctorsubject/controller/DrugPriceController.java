package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.DrugPrice;
import com.familydoctor.doctorsubject.service.DrugPriceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 药品价格(familydoctor/drugprice)
 * 添加(add),修改(update),删除(softdelete),id查询(selectbyid),查询所有(selectall),分页查询(selectpage)
 */
@RestController
@RequestMapping(value = "familydoctor/drugprice")
public class DrugPriceController extends BaseController {

    @Autowired
    private DrugPriceService drugPriceService;

    /**
     * 添加药品价格
     *
     * @param drugPrice
     */
    @PostMapping(value = "add")
    public Map addDrugPrice(DrugPrice drugPrice) {

        if (drugPrice == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = drugPriceService.addDrugPrice(drugPrice);
        if (i > 0) {
            return requestInsertSuccess("添加成功");
        }
        return requestInsertFail("添加失败");
    }


    /**
     * 删除药品价格
     *
     * @param drugPrice
     */
    @GetMapping(value = "softdelete")
    public Map deleteDrugPrice(DrugPrice drugPrice) {

        if (StringUtils.isBlank(drugPrice.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        drugPrice.setDeleteTime(addTime());
        int i = drugPriceService.softDel(drugPrice);
        if (i > 0) {
            return requestDeleteSuccess("删除成功");
        }
        return requestDeleteFail("添加失败");
    }


    /**
     * 更新未使用药品价格
     *
     * @param drugPrice
     */
    @PostMapping(value = "update")
    public Map updateDrugPrice(DrugPrice drugPrice) {

        if (drugPrice == null) {
            return requestArgumentEmpty("参数为空");
        }

        drugPrice.setUpdateTime(addTime());
        int i = drugPriceService.updateDp(drugPrice);
        if (i > 0) {
            return requestUpdateSuccess("更新成功");
        }
        return requestUpdateFail("更新失败");
    }


    /**
     * 由ID查询对应的药品价格
     *
     * @param drugPrice
     */
    @GetMapping(value = "selectbyid")
    public Map selectById(DrugPrice drugPrice) {

        if (StringUtils.isBlank(drugPrice.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        DrugPrice resultDrugPrice = drugPriceService.selectById(drugPrice.getId());
        if (resultDrugPrice != null) {
            return requestSelectSuccess(resultDrugPrice);
        }
        return requestSelectFail("查询失败");
    }


    /**
     * 查询所有药品价格
     */
    @GetMapping(value = "selectall")
    public Map selectAllDrugPrice() {

        List<DrugPrice> drugPriceList = drugPriceService.selectAllPrice();
        if (drugPriceList != null && !drugPriceList.isEmpty()) {
            return requestSelectSuccess(drugPriceList);
        }
        return requestSelectFail("查询失败");
    }


    /**
     * 重新设置已经使用的药品价格
     *
     * @param drugPrice
     */
    @PostMapping(value = "updateuse")
    public Map reinstallDrugPrice(String drugPriceId, DrugPrice drugPrice) {

        DrugPrice oldDrugPrice = drugPriceService.selectById(drugPriceId);

        //删除DrugPrice.Id对应旧的药品价格
        oldDrugPrice.setDeleteTime(addTime());
        if (oldDrugPrice.getStopTime().getTime() > drugPrice.getStartTime().getTime()) {
            oldDrugPrice.setStopTime(drugPrice.getStartTime());
        }
        drugPriceService.softDel(oldDrugPrice);

        //重置药品价格
        int i = drugPriceService.addDrugPrice(drugPrice);
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
    @GetMapping(value = "selectpage")
    public Map selectPage(DrugPrice drugPrice) {

        List<DrugPrice> drugPriceList = drugPriceService.selectPage(drugPrice);

        if (drugPriceList != null && !drugPriceList.isEmpty()) {
            return requestSelectSuccess(drugPriceList);
        }

        return requestSelectFail("查询失败");
    }
}
