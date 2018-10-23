package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.Hospital;
import com.familydoctor.doctorsubject.service.HospitalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 医院
 */
@RestController
@RequestMapping(value = "familydoctor/hospital")
public class HospitalController extends BaseController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 添加医院
     *
     * @param hospital
     * @return requestInsertSuccess(hospital)
     */
    @PostMapping(value = "add")
    public Map addHospitalMsg(Hospital hospital) {

        if (hospital == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = hospitalService.addHospital(hospital);
        if (i > 0) {
            return requestInsertSuccess(hospital);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 删除医院
     *
     * @param hospital
     * @return requestDeleteSuccess(hospital)
     */
    @GetMapping(value = "softDel")
    public Map softDelHospital(Hospital hospital) {

        if (StringUtils.isBlank(hospital.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        hospital.setDeleteTime(addTime());
        int i = hospitalService.softDel(hospital);
        if (i > 0) {
            return requestDeleteSuccess(hospital);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 修改医院
     *
     * @param hospital
     * @return requestUpdateSuccess(hospital)
     */
    @PostMapping(value = "update")
    public Map updateHospitalMsg(Hospital hospital) {

        if (StringUtils.isBlank(hospital.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        hospital.setUpdateTime(addTime());
        int i = hospitalService.updateHospital(hospital);
        if (i > 0) {
            return requestUpdateSuccess(hospital);
        } else {
            return requestUpdateFail("修改失败");
        }

    }

    /**
     * 由Id查询对应的医院
     *
     * @param hospital
     * @return requestSelectSuccess(resultHospital)
     */
    @GetMapping(value = "selectById")
    public Map selectHospitalById(Hospital hospital) {

        if (StringUtils.isBlank(hospital.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        Hospital resultHospital = hospitalService.selectHospital(hospital);
        if (resultHospital != null) {
            return requestSelectSuccess(resultHospital);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 查询所有医院
     *
     * @return requestSelectSuccess(hospitalList)
     */
    @GetMapping(value = "selectAll")
    public Map selectAllHospital(String pageNo, String pageSize) {
        List<Hospital> hospitalList = hospitalService.selectAll(pageNo, pageSize);
        if (hospitalList != null && !hospitalList.isEmpty()) {
            return requestSelectSuccess(hospitalList);
        } else {
            return requestSelectFail("查询失败");
        }

    }
}
