package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.Doctor;
import com.familydoctor.doctorsubject.entity.Produce;
import com.familydoctor.doctorsubject.service.ProduceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 套餐
 */
@RestController
@RequestMapping(value = "familydoctor/produce")
public class ProduceController extends BaseController {

    @Autowired
    private ProduceService produceService;

    /**
     * 医生套餐添加
     *
     * @param produce
     * @return
     */
    @PostMapping(value = "add")
    public Map addProduceMsg(Produce produce) {

        produce.setDoctorId(getCurrentUser());
        produce.setCreateUser(getCurrentUser());
        int i = produceService.insertByDoctor(produce);
        if (i > 0) {
            return requestInsertSuccess(produce);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 医生套餐列表查询
     *
     * @param produce
     * @return requestSelectSuccess(produceList)
     */
    @GetMapping(value = "selectByDoctor")
    public Map selectProduceByDoctor(Produce produce) {

        if (StringUtils.isBlank(produce.getDoctorId())) {
            return requestArgumentEmpty("doctorId为空");
        }

        List<Produce> produceList = produceService.selectByDoctorId(produce.getDoctorId());
        if (produceList != null && !produceList.isEmpty()) {
            return requestSelectSuccess(produceList);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 由Id查询套餐
     *
     * @param produce
     * @return requestSelectSuccess(resultProduce)
     */
    @GetMapping(value = "selectById")
    public Map selectProduceById(Produce produce) {

        if (StringUtils.isBlank(produce.getId())) {
            return requestSelectFail("Id为空");
        }

        Produce resultProduce = produceService.selectById(produce.getId());
        if (resultProduce != null) {
            return requestSelectSuccess(resultProduce);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 删除套餐
     *
     * @param produce
     * @return requestDeleteSuccess(produce)
     */
    @GetMapping(value = "softDel")
    public Map softDelProduce(Produce produce) {

        if (StringUtils.isBlank(produce.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        produce.setDeleteTime(addTime());
        int i = produceService.softDel(produce);
        if (i > 0) {
            return requestDeleteSuccess(produce);
        } else {
            return requestDeleteFail("删除失败");
        }

    }


    /**
     * 修改套餐
     *
     * @param produce
     * @return requestUpdateSuccess(produce)
     */
    @PostMapping(value = "update")
    public Map updateHospitalMsg(Produce produce) {

        if (StringUtils.isBlank(produce.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        produce.setUpdateTime(addTime());
        int i = produceService.updateById(produce);
        if (i > 0) {
            return requestUpdateSuccess(produce);
        } else {
            return requestUpdateFail("修改失败");
        }

    }
}
