package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.entity.PriceType;
import com.familydoctor.doctorsubject.service.PriceTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 缴费类型
 */
@RestController
@RequestMapping(value = "familydoctor/priceType")
public class PriceTypeController extends BaseController {

    @Autowired
    private PriceTypeService priceTypeService;

    /**
     * 添加缴费类型
     *
     * @param priceType
     * @return requestInsertSuccess(priceType)
     */
    @PostMapping(value = "add")
    public Map addPriceType(PriceType priceType) {

        if (priceType == null) {
            return requestArgumentEmpty("priceType为空");
        }

        int i = priceTypeService.insertSelective(priceType);
        if (i > 0) {
            return requestInsertSuccess(priceType);
        } else {
            return requestSelectFail("添加priceType失败");
        }

    }

    /**
     * 删除缴费类型
     *
     * @param priceType
     * @return requestDeleteSuccess(priceType)
     */
    @GetMapping(value = "softDel")
    public Map softDelPriceType(PriceType priceType) {

        if (StringUtils.isBlank(priceType.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        priceType.setDeleteTime(addTime());
        int i = priceTypeService.softDeleteById(priceType);
        if (i > 0) {
            return requestDeleteSuccess(priceType);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 物理删除缴费类型
     *
     * @param priceType
     * @return requestDeleteSuccess(priceType)
     */
    @GetMapping(value = "Del")
    public Map DelPriceType(PriceType priceType) {

        if (StringUtils.isBlank(priceType.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        priceType.setDeleteTime(addTime());
        int i = priceTypeService.deleteByPrimaryKey(priceType.getId());
        if (i > 0) {
            return requestDeleteSuccess(priceType);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 修改缴费类型
     *
     * @param priceType
     * @return requestUpdateSuccess(priceType)
     */
    @PostMapping(value = "update")
    public Map updatePriceType(PriceType priceType) {

        if (StringUtils.isBlank(priceType.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        priceType.setUpdateTime(addTime());
        int i = priceTypeService.updateByPrimaryKeySelective(priceType);
        if (i > 0) {
            return requestUpdateSuccess(priceType);
        } else {
            return requestUpdateFail("修改失败");
        }

    }

    /**
     * 分页查询
     *
     * @param priceType
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(PriceType priceType) {

        List<PriceType> priceTypeList = priceTypeService.selectPage(priceType);

        if (priceTypeList != null && !priceTypeList.isEmpty()) {
            return requestSelectSuccess(priceTypeList);
        }

        return requestSelectFail("查询失败");
    }
}
