package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.bean.MemberPriceBean;
import com.familydoctor.doctorsubject.entity.*;
import com.familydoctor.doctorsubject.service.*;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "familydoctor/memberPrice")
public class MemberPriceController extends BaseController {

    @Autowired
    private MemberPriceService memberPriceService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ProduceService produceService;

    @Autowired
    private PriceTypeService priceTypeService;

    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * 查询缴费member
     *
     * @param member
     * @param cases
     * @return
     */
    @GetMapping(value = "selectPayMember")
    public Map selectPayMember(Member member, Cases cases) {

        if (StringUtils.isBlank(member.getId()) || StringUtils.isBlank(cases.getId())) {
            return requestArgumentEmpty("memberId与casesId为空");
        }

        //memberId查询contract列表
        List<Contract> contractList = contractService.selectByMemberId(member);
        if (contractList == null || contractList.isEmpty()) {
            return requestSelectFail("memberId查询contract列表为空");
        }

        //查询套餐
        String produceID = contractList.get(0).getProduceId();
        Produce produce = produceService.selectById(produceID);
        if (produce == null) {
            return requestSelectFail("查询Produce失败");
        }

        String priceTypeId;
        MemberPriceBean memberPriceBean = new MemberPriceBean();
        PriceType priceType = new PriceType();
        //查询priceTypeName为"年费","普通"的PriceTypeId
        if (produce.getProduceName().equals("年费")) {
            priceType.setPriceTypeName("produce.getProduceName()");
            priceTypeId = priceTypeService.selectParam(priceType).getId();
            memberPriceBean.setPriceTypeId(priceTypeId);
            memberPriceBean.setProduceId(produce.getId());
        } else if (produce.getProduceName().equals("普通")) {
            priceType.setPriceTypeName("produce.getProduceName()");
            priceTypeId = priceTypeService.selectParam(priceType).getId();
            memberPriceBean.setPriceTypeId(priceTypeId);
            memberPriceBean.setProduceId(produce.getId());

        } else {
            return requestSelectFail("查询PriceType失败或priceTypeName不是'年费','普通'");
        }

        //获取处方信息
        Prescription prescription = new Prescription();
        prescription.setCaseId(cases.getId());
        List<Prescription> prescriptionList = prescriptionService.selectToCases(prescription);
        if (prescriptionList == null || prescriptionList.isEmpty()) {
            return requestSelectFail("casesId查询Prescrption失败");
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Prescription resuPrescription : prescriptionList) {
            stringBuffer.append(resuPrescription.getId());
            stringBuffer.append("-");
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        String[] orderIds = stringBuffer.toString().split("-");
        memberPriceBean.setOrderIds(orderIds);
        memberPriceBean.setMemberId(member.getId());

        List<MemberPrice> memberPriceList = memberPriceService.selectThreePram(memberPriceBean);
        if (memberPriceList == null || memberPriceList.isEmpty()) {
            return requestSelectFail("memberId,orderId,priceTyoeId查询结果为空");
        }

        return requestSelectSuccess(memberPriceList);

    }

    /**
     * 付款状态更改
     *
     * @param ids
     */
    @PostMapping(value = "updatePay")
    public Map payTheFess(String ids) {

        if (StringUtils.isBlank(ids)) {
            return requestArgumentEmpty("memberpreiceIds为空");
        }
        MemberPrice memberPrice = new MemberPrice();
        String[] memberPriceIds = ids.split("-");
        for (String id : memberPriceIds) {
            memberPrice.setId(id);
            memberPrice.setIspay("1");
            int i = memberPriceService.updateByPrimaryKeySelective(memberPrice);
            if (i < 1) {
                return requestUpdateFail("付款失败");
            }
        }

        return requestUpdateSuccess("成功付款");
    }

/*    @GetMapping(value = "selectPrice")
    public Map selectPrice() {
    }*/
}
