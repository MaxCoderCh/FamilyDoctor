package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.Usages;
import com.familydoctor.doctorsubject.service.UsagesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 药品用法用量
 */
@RestController
@RequestMapping(value = "familydoctor/usages")
public class UsagesController extends BaseController {

    @Autowired
    private UsagesService usagesService;

    /**
     * 添加药品用法用量
     *
     * @param usages
     * @return requestSelectSuccess(usages)
     */
    @PostMapping(value = "add")
    public Map addUsagesMsg(Usages usages) {

        if (usages == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = usagesService.addSelect(usages);
        if (i > 0) {
            return requestSelectSuccess(usages);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 物理删除药品用法用量
     *
     * @param usages
     * @return requestDeleteSuccess(usages)
     */
    @GetMapping(value = "Del")
    public Map DelUsages(Usages usages) {

        if (StringUtils.isBlank(usages.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        usages.setDeleteTime(addTime());
        int i = usagesService.deleteById(usages.getId());
        if (i > 0) {
            return requestDeleteSuccess(usages);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 删除药品用法用量
     *
     * @param usages
     * @return requestDeleteSuccess(usages)
     */
    @GetMapping(value = "softDel")
    public Map softDelUsages(Usages usages) {

        if (StringUtils.isBlank(usages.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        usages.setDeleteTime(addTime());
        int i = usagesService.softDel(usages);
        if (i > 0) {
            return requestDeleteSuccess(usages);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 修改药品用法用量
     *
     * @param usages
     * @return requestUpdateSuccess(lable)
     */
    @PostMapping(value = "update")
    public Map updateUsages(Usages usages) {

        if (StringUtils.isBlank(usages.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        usages.setUpdateTime(addTime());
        int i = usagesService.updateById(usages);
        if (i > 0) {
            return requestUpdateSuccess(usages);
        } else {
            return requestUpdateFail("修改失败");
        }

    }

    /**
     * 由Id查询
     *
     * @param usages
     * @return requestSelectSuccess(resultUsages)
     */
    @GetMapping(value = "selectById")
    public Map selectUsagesById(Usages usages) {

        if (StringUtils.isBlank(usages.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        Usages resultUsages = usagesService.selectById(usages.getId());
        if (resultUsages != null) {
            return requestSelectSuccess(resultUsages);
        } else {
            return requestSelectFail("查询失败");
        }

    }

}
