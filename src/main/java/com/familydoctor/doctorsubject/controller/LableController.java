package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.Lable;
import com.familydoctor.doctorsubject.service.LableService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 标签
 */
@RestController
@RequestMapping(value = "familydoctor/lable")
public class LableController extends BaseController {

    @Autowired
    private LableService lableService;

    /**
     * 添加标签
     *
     * @param lable
     * @return requestSelectSuccess(lableClass)
     */
    @PostMapping(value = "add")
    public Map addLLableMsg(Lable lable) {

        if (lable == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = lableService.addLable(lable);
        if (i > 0) {
            return requestSelectSuccess(lable);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 删除标签
     *
     * @param lable
     * @return requestDeleteSuccess(lableClass)
     */
    @GetMapping(value = "softDel")
    public Map softDelLable(Lable lable) {

        if (StringUtils.isBlank(lable.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        lable.setDeleteTime(addTime());
        int i = lableService.softDel(lable);
        if (i > 0) {
            return requestDeleteSuccess(lable);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 修改标签
     *
     * @param lable
     * @return requestUpdateSuccess(lable)
     */
    @PostMapping(value = "update")
    public Map updateLable(Lable lable) {

        if (StringUtils.isBlank(lable.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        lable.setUpdateTime(addTime());
        int i = lableService.updateById(lable);
        if (i > 0) {
            return requestUpdateSuccess(lable);
        } else {
            return requestUpdateFail("修改失败");
        }

    }

    /**
     * 由Id查询对应的标签
     *
     * @param lable
     * @return requestSelectSuccess(resultLable)
     */
    @GetMapping(value = "selectById")
    public Map selectLableById(Lable lable) {

        if (StringUtils.isBlank(lable.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        Lable resultLable = lableService.selectById(lable);
        if (resultLable != null) {
            return requestSelectSuccess(resultLable);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 查询全部标签
     *
     * @return requestSelectSuccess(lableList)
     */
    @GetMapping(value = "selectAll")
    public Map selectAllLable() {

        List<Lable> lableList = lableService.selectAll();
        if (lableList != null && !lableList.isEmpty()) {
            return requestSelectSuccess(lableList);
        } else {
            return requestSelectFail("查询失败");
        }
    }

    //由多个lablename获取对应的lableId
    @GetMapping(value = "selectByName")
    public Map selectByLableName(String[] lableNames) {

        if (lableNames == null) {
            return requestArgumentEmpty("标签名为空");
        }

        List<Lable> resultLable = lableService.selectByName(lableNames);
        if (resultLable == null || resultLable.isEmpty()) {
            return requestSelectFail("查询结果为空");
        }

        return requestSelectSuccess(resultLable);
    }

    /**
     * 分页查询
     *
     * @param lable
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(Lable lable) {

        List<Lable> lableList = lableService.selectPage(lable);

        if (lableList != null && !lableList.isEmpty()) {
            return requestSelectSuccess(lableList);
        }

        return requestSelectFail("查询失败");
    }

}
