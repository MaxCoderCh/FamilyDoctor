package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.entity.LableClass;
import com.familydoctor.doctorsubject.service.LableClassService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 标签类别(familydoctor/lableclass)
 */
@RestController
@RequestMapping(value = "familydoctor/lableclass")
public class LableClassController extends BaseController {

    @Autowired
    private LableClassService lableClassService;

    /**
     * 添加标签类别
     *
     * @param lableClass
     */
    @PostMapping(value = "add")
    public Map addLLableClassMsg(LableClass lableClass) {

        if (lableClass == null) {
            return requestArgumentEmpty("参数为空");
        }

        lableClass.setCreateUser(getCurrentUser());
        int i = lableClassService.addLableClass(lableClass);
        if (i > 0) {
            return requestSelectSuccess("添加成功");
        }
        return requestInsertFail("添加失败");
    }


    /**
     * 删除标签类别
     *
     * @param lableClass
     */
    @GetMapping(value = "softdelete")
    public Map softDelLableClass(LableClass lableClass) {

        if (StringUtils.isBlank(lableClass.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        lableClass.setDeleteTime(addTime());
        int i = lableClassService.softDel(lableClass);
        if (i > 0) {
            return requestDeleteSuccess("删除成功");
        }
        return requestDeleteFail("删除失败");
    }


    /**
     * 修改标签类别
     *
     * @param lableClass
     */
    @PostMapping(value = "update")
    public Map updateLableClass(LableClass lableClass) {

        if (StringUtils.isBlank(lableClass.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        lableClass.setUpdateTime(addTime());
        int i = lableClassService.updateById(lableClass);
        if (i > 0) {
            return requestUpdateSuccess("修改成功");
        }
        return requestUpdateFail("修改失败");
    }

    /**
     * 由Id查询对应的标签类别
     *
     * @param lableClass
     */
    @GetMapping(value = "selectbyid")
    public Map selectLableClassById(LableClass lableClass) {

        if (StringUtils.isBlank(lableClass.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        LableClass resultLableClass = lableClassService.selectById(lableClass.getId());
        if (resultLableClass != null) {
            return requestSelectSuccess(resultLableClass);
        }
        return requestSelectFail("查询失败");


    }

    /**
     * 查询全部标签类别
     */
    @GetMapping(value = "selectAll")
    public Map selectAllLableClass() {

        List<LableClass> lableClassList = lableClassService.selectAll();
        if (lableClassList != null && !lableClassList.isEmpty()) {
            return requestSelectSuccess(lableClassList);
        }
        return requestSelectFail("查询失败");
    }

    /**
     * 分页查询
     *
     * @param lableClass
     */
    @GetMapping(value = "selectpage")
    public Map selectPage(LableClass lableClass) {

        List<LableClass> lableClassList = lableClassService.selectPage(lableClass);

        if (lableClassList != null && !lableClassList.isEmpty()) {
            return requestSelectSuccess(lableClassList);
        }

        return requestSelectFail("查询失败");
    }
}
