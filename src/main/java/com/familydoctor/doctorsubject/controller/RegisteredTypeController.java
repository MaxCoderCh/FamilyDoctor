package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.RegisteredType;
import com.familydoctor.doctorsubject.service.RegisteredTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 挂号类型
 */
@RestController
@RequestMapping(value = "familydoctor/registeredType")
public class RegisteredTypeController extends BaseController {

    @Autowired
    private RegisteredTypeService registeredTypeService;

    @PostMapping(value = "add")
    public Map addHospitalMsg(RegisteredType RegisteredType) {

        if (RegisteredType == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = registeredTypeService.addRegisteredType(RegisteredType);
        if (i > 0) {
            return requestInsertSuccess(RegisteredType);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 删除挂号类型
     *
     * @param registeredType
     * @return requestDeleteSuccess(hospital)
     */
    @GetMapping(value = "softDel")
    public Map softDelRegisteredType(RegisteredType registeredType) {

        if (StringUtils.isBlank(registeredType.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        registeredType.setDeleteTime(addTime());
        int i = registeredTypeService.softDel(registeredType);
        if (i > 0) {
            return requestDeleteSuccess(registeredType);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 修改挂号类别
     *
     * @param registeredType
     * @return requestUpdateSuccess(registeredType)
     */
    @PostMapping(value = "update")
    public Map updateRegisteredType(RegisteredType registeredType) {

        if (StringUtils.isBlank(registeredType.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        registeredType.setUpdateTime(addTime());
        int i = registeredTypeService.updateById(registeredType);
        if (i > 0) {
            return requestUpdateSuccess(registeredType);
        } else {
            return requestUpdateFail("修改失败");
        }

    }

    /**
     * 由Id查询对应挂号类别
     *
     * @param registeredType
     * @return requestSelectSuccess(resultRegisteredType)
     */
    @GetMapping(value = "selectById")
    public Map selectRegisteredTypeById(RegisteredType registeredType) {

        if (StringUtils.isBlank(registeredType.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        RegisteredType resultRegisteredType = registeredTypeService.selectById(registeredType.getId());
        if (resultRegisteredType != null) {
            return requestSelectSuccess(resultRegisteredType);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 由branch查询所有挂号类别
     *
     * @param registeredType
     * @return requestSelectSuccess(registeredTypeList)
     */
    @GetMapping(value = "selectByBranch")
    public Map selectRegisteredTypeByBranch(RegisteredType registeredType) {

        if (StringUtils.isBlank(registeredType.getBranchId())) {
            return requestArgumentEmpty("参数为空");
        }

        List<RegisteredType> registeredTypeList = registeredTypeService.selectByBranchId(registeredType.getBranchId());
        if (registeredTypeList != null && !registeredTypeList.isEmpty()) {
            return requestSelectSuccess(registeredTypeList);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 查询所有挂号类别
     *
     * @return requestSelectSuccess(registeredTypeList)
     */
    @GetMapping(value = "selectAll")
    public Map selectAllRegisteredType() {

        List<RegisteredType> registeredTypeList = registeredTypeService.selectAll();
        if (registeredTypeList != null && !registeredTypeList.isEmpty()) {
            return requestSelectSuccess(registeredTypeList);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 分页查询
     *
     * @param registeredType
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(RegisteredType registeredType) {

        List<RegisteredType> registeredTypeList = registeredTypeService.selectPage(registeredType);

        if (registeredTypeList != null && !registeredTypeList.isEmpty()) {
            return requestSelectSuccess(registeredTypeList);
        }

        return requestSelectFail("查询失败");
    }

}
