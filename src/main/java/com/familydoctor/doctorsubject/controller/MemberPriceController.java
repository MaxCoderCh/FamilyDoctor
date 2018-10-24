package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.YoonaLTsUtils.DateUtils;
import com.familydoctor.doctorsubject.bean.ContractBean;
import com.familydoctor.doctorsubject.bean.MemberPriceBean;
import com.familydoctor.doctorsubject.entity.*;
import com.familydoctor.doctorsubject.service.*;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private MemberService memberService;

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
            priceTypeId = priceTypeService.selectParam(priceType).get(0).getId();
            memberPriceBean.setPriceTypeId(priceTypeId);
            memberPriceBean.setProduceId(produce.getId());
        } else if (produce.getProduceName().equals("普通")) {
            priceType.setPriceTypeName("produce.getProduceName()");
            priceTypeId = priceTypeService.selectParam(priceType).get(0).getId();
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


    /**
     * 根据条件(身份证号.日期.费用类型)查询列表
     *
     * @param memberCard
     * @param priceTypeName
     * @param startTime
     * @param stopTime
     */
    @GetMapping(value = "selectPrice")
    public Map selectPrice(String memberCard, String priceTypeName, String startTime, String stopTime) {
        if (StringUtils.isBlank(memberCard) || StringUtils.isBlank(priceTypeName) || StringUtils.isBlank(startTime) || StringUtils.isBlank(stopTime)) {
            return requestArgumentEmpty("memberCard与priceTypeName为空");
        }

        MemberPriceBean memberPriceBean = new MemberPriceBean();

        //时间格式转换
        Date startDate = DateUtils.stringToDate(startTime);
        Date endDate = DateUtils.stringToDate(stopTime);
        memberPriceBean.setStartDate(startDate);
        memberPriceBean.setEndDate(endDate);

        // 获取memnberId
        Member member = new Member();
        member.setMemberCard(memberCard);
        String memberId = memberService.selectByCard(member).getId();
        if (StringUtils.isBlank(memberCard)) {
            return requestSelectFail("memberCard获取memberId失败");
        }
        memberPriceBean.setMemberId(memberId);

        //获取与priceTypeName对应的priceTypeId
        PriceType priceType = new PriceType();
        switch (priceTypeName) {
            case "0":
                priceType.setPriceTypeName("会员费用");
                String priceTypeIdOne = priceTypeService.selectParam(priceType).get(0).getId();
                memberPriceBean.setPriceTypeId(priceTypeIdOne);
                break;
            case "1":
                priceType.setPriceTypeName("诊疗费用");
                String priceTypeIdTwo = priceTypeService.selectParam(priceType).get(0).getId();
                memberPriceBean.setPriceTypeId(priceTypeIdTwo);
                break;
            case "2":
                priceType.setPriceTypeName("药品费用");
                String priceTypeIdThree = priceTypeService.selectParam(priceType).get(0).getId();
                memberPriceBean.setPriceTypeId(priceTypeIdThree);
                break;
            default:
                return requestArgumentError("传入priceTypeName错误或查询priceTypeId出错");
        }

        //查询MemberPrice列表
        List<MemberPrice> memberPriceList = memberPriceService.selectTrends(memberPriceBean);
        if (memberPriceList == null || memberPriceList.isEmpty()) {
            return requestSelectFail("memberId,priceTypeName查询memberPrice失败");
        }

        return requestSelectSuccess(memberPriceList);
    }

    /**
     * 费用类型查询医生收益
     *
     * @param priceTypeName
     * @param startTime
     * @param stopTime
     */
    @GetMapping(value = "selectDoctorIncome")
    public Map DoctorIncome(String priceTypeName, String startTime, String stopTime) {

        if (StringUtils.isBlank(priceTypeName) || StringUtils.isBlank(startTime) || StringUtils.isBlank(stopTime)) {
            return requestArgumentEmpty("参数为空");
        }

        MemberPriceBean memberPriceBean = new MemberPriceBean();
        Date startDate = DateUtils.stringToDate(startTime);
        Date endDate = DateUtils.stringToDate(stopTime);
        memberPriceBean.setStartDate(startDate);
        memberPriceBean.setEndDate(endDate);

        //由priceTypeName查询MemberPrice
        PriceType priceType = new PriceType();
        if (priceTypeName.equals("0")) {// 年费
            priceType.setPriceTypeName("会员费用");
        } else if (priceTypeName.equals("1")) {// 单次
            priceType.setPriceTypeName("诊疗费用");
        } else if (priceTypeName.equals("2")) {// 药品
            priceType.setPriceTypeName("药品费用");
        }
        String priceTypeId = priceTypeService.selectParam(priceType).get(0).getId();
        if (StringUtils.isBlank(priceTypeId)) {
            return requestSelectFail("查询的priceTypeId为空");
        }

        memberPriceBean.setPriceTypeId(priceTypeId);

        List<MemberPrice> memberPriceList = memberPriceService.selectTrends(memberPriceBean);
        if (memberPriceList == null || memberPriceList.isEmpty()) {
            return requestSelectFail("startTime,stopTime,priceTypeName查询的结果为空");
        }

        //合并memberPrice同一memberId的price项
        List<String> memberIds = new ArrayList<>();
        List<MemberPrice> setMemberPrice = new ArrayList<>();
        for (MemberPrice memberPrice : memberPriceList) {
            memberIds.add(memberPrice.getMemberId());
        }
        HashSet ids = new HashSet(memberIds);
        memberIds.clear();
        memberIds.addAll(ids);

        return requestArgumentError("未完成");
    }

    /**
     * 获取当天统计数据
     */
    @GetMapping(value = "intradayData")
    public Map intradayData() {
        Date startDate = DateUtils.intradayBegin();
        Date endDate = DateUtils.intradayEnd();

        //查询Contract
        ContractBean contractBean = new ContractBean();
        contractBean.setCreateUser(getCurrentUser());
        contractBean.setStartDateL(startDate);
        contractBean.setEndDateL(endDate);
        List<Contract> contractList = contractService.selectTrends(contractBean);

        //查询MemberPrice
        MemberPriceBean memberPriceBean = new MemberPriceBean();
        memberPriceBean.setStartDate(startDate);
        memberPriceBean.setEndDate(endDate);
        memberPriceBean.setCreateDoctor(getCurrentUser());
        List<MemberPrice> memberPriceList = memberPriceService.selectTrends(memberPriceBean);

        //药品费用去重Map
        Map<String, String> drugMap = new LinkedHashMap<String, String>();
        //会员费用去重Map
        Map<String, String> memberMap = new LinkedHashMap<String, String>();
        //诊疗费用去重Map
        Map<String, String> diagnosisMap = new LinkedHashMap<String, String>();
        return requestArgumentError("去重");
    }

    //获取时间段内药品使用
    @GetMapping(value = "drugUse")
    public Map selectDrugUse(String startTime, String stopTime, String drugId) {
        return null;
    }
}
