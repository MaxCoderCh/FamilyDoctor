package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.*;
import com.familydoctor.doctorsubject.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 处方药品
 */
@RestController
@RequestMapping(value = "familydoctor/prescription")
public class PrescriptionController extends BaseController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private DrugPriceService drugPriceService;

    @Autowired
    private PriceTypeService priceTypeService;

    @Autowired
    private CasesService casesService;

    @Autowired
    private MemberPriceService memberPriceService;

    @Autowired
    private DrugService drugService;

    /**
     * 添加处方药品
     *
     * @param prescription
     * @return requestInsertSuccess(prescription)
     */
    @PostMapping(value = "add")
    public Map addPrescriptionMsg(Prescription prescription) {


        if (StringUtils.isBlank(prescription.getCaseId()) || StringUtils.isBlank(prescription.getDrugId())) {
            return requestArgumentEmpty("参数为空");
        }

        //疾病对象,MemberPrice对象,药品价格对象
        Cases cases = casesService.selectById(prescription.getCaseId());
        MemberPrice memberPrice = new MemberPrice();
        DrugPrice drugPrice = new DrugPrice();

        //药品价格设置
        drugPrice.setDrugId(prescription.getDrugId());
        drugPrice.setStartTime(addTime());
        List<DrugPrice> drugPriceList = drugPriceService.getByParam(drugPrice);

        //缴费类型设置
        PriceType priceType = new PriceType();
        priceType.setPriceTypeNumber("00001");
        priceType = priceTypeService.selectParam(priceType).get(0);

        //添加会员价格
        memberPrice.setMemberId(cases.getMemberId());
        memberPrice.setPriceTypeId(priceType.getId());
        memberPrice.setPrice(drugPriceList.get(0).getDrugOutPrice());
        //是否需要查询?
        memberPrice.setOrderId(prescription.getId());
        memberPrice.setCreateTime(addTime());
        memberPrice.setCreateDoctor(getCurrentUser());

        int j = memberPriceService.addMemberPrice(memberPrice);
        if (j < 1) {
            return requestInsertFail("添加memberPrice失败");
        }

        int i = prescriptionService.addPrescription(prescription);
        if (i > 0) {
            return requestInsertSuccess(prescription);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 删除处方信息
     *
     * @param prescription
     * @return requestDeleteSuccess(prescription)
     */
    @GetMapping(value = "softDel")
    public Map softDelPrescription(Prescription prescription) {

        if (StringUtils.isBlank(prescription.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        int i = prescriptionService.softDel(prescription);
        if (i > 0) {
            return requestDeleteSuccess(prescription);
        } else {
            return requestDeleteFail("删除失败");
        }
    }

    /**
     * 由疾病Id查询处方(既处方所属疾病)
     *
     * @param prescription
     * @return
     */
    @GetMapping(value = "selectCases")
    public Map selectPrescriptionByCases(Prescription prescription) {

        if (StringUtils.isBlank(prescription.getCaseId())) {
            return requestArgumentEmpty("casesId为空");
        }

        List<Prescription> prescriptionList = prescriptionService.selectToCases(prescription);
        if (prescriptionList != null && !prescriptionList.isEmpty()) {

            //检查处方药品是否可修改
            for (Prescription ption : prescriptionList) {

                MemberPrice memberPrice = new MemberPrice();
                memberPrice.setOrderId(ption.getId());
                List<MemberPrice> memberPriceList = memberPriceService.selectBySelective(memberPrice);

                if (memberPriceList.get(0).getIspay().equals("1")) {
                    return BadRequest("该处方药品已经结算,不可以修改");
                }

            }

            return requestSelectSuccess(prescriptionList);
        }

        return requestSelectFail("查询失败");
    }

}

