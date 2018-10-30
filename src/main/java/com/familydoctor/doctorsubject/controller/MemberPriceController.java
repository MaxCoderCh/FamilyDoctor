package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.YoonaLTsUtils.DateUtils;
import com.familydoctor.doctorsubject.bean.ContractBean;
import com.familydoctor.doctorsubject.bean.DoctorOutCallBean;
import com.familydoctor.doctorsubject.bean.MemberPriceBean;
import com.familydoctor.doctorsubject.bean.PrescriptionBean;
import com.familydoctor.doctorsubject.entity.*;
import com.familydoctor.doctorsubject.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
     * 查询需要缴费
     *
     * @param member
     * @param cases
     * @return
     */
    @GetMapping(value = "selectpaymember")
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

        MemberPriceBean memberPriceBean = new MemberPriceBean();
        //获取会员套餐类型,年费免收费,普通收取produce-price的费用
        if (produce.getProduceName().equals("年费")) {
            memberPriceBean.setProduceType("年费");
            memberPriceBean.setProducePrice("0");
        } else if (produce.getProduceName().equals("普通")) {
            memberPriceBean.setProduceType("普通");
            Produce produce1 = new Produce();
            produce.setProduceName("普通");
            String id = produceService.selectByName(produce1);
            String price = produceService.selectById(id).getProducePrice();
            memberPriceBean.setProducePrice(price);

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
    @PostMapping(value = "updatepay")
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
     * 根据条件(身份证号.日期.费用类型)查询会员收费信息
     *
     * @param memberCard
     * @param priceTypeName
     * @param startTime
     * @param stopTime
     */
    @GetMapping(value = "selectprice")
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

        // 通过身份证号获取member
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
    @GetMapping(value = "selectoctorIincome")
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

        // 查询符合条件的memberPrice列表
        memberPriceBean.setPriceTypeId(priceTypeId);
        List<MemberPrice> memberPriceList = memberPriceService.selectTrends(memberPriceBean);
        if (memberPriceList == null || memberPriceList.isEmpty()) {
            return requestSelectFail("startTime,stopTime,priceTypeName查询的结果为空");
        }

        boolean yesOrNot;
        //合并memberPrice同一memberId的price项
        Map<String, String> earnings = new HashMap<>();
        for (MemberPrice memberPrice : memberPriceList) {
            yesOrNot = earnings.containsKey(memberPrice.getMemberId());

            if (yesOrNot) {
                String price = memberPrice.getPrice();
                String formerPrice = earnings.get(memberPrice.getMemberId());
                int i = Integer.parseInt(price);
                int j = Integer.parseInt(formerPrice);
                String nowPrice = String.valueOf(i + j);
                earnings.put(memberPrice.getMemberId(), nowPrice);
            } else {
                earnings.put(memberPrice.getMemberId(), memberPrice.getPrice());
            }

        }

        return requestSuccessful(earnings);
    }


    /**
     * 当天统计数据,签约数,签约人数,药品总价,会员费用总价,诊疗费用总价,
     *
     * @return
     */
    @GetMapping(value = "intradaydata")
    public Map intradayData() {
        Date startDate = DateUtils.intradayBegin();
        Date endDate = DateUtils.intradayEnd();

        //查询Contract
        ContractBean contractBean = new ContractBean();
        contractBean.setCreateUser(getCurrentUser());
        contractBean.setStartDateL(startDate);
        contractBean.setEndDateL(endDate);
        List<Contract> contractList = contractService.selectTrends(contractBean);
        // 签约数
        int amountContract = contractList.size();

        List<String> members = new ArrayList<>();
        for (Contract contract : contractList) {
            members.add(contract.getMemberId());
        }
        HashSet h = new HashSet(members);
        members.clear();
        members.addAll(h);
        // 签约人数
        int memberCount = members.size();

        //查询MemberPrice
        MemberPriceBean memberPriceBean = new MemberPriceBean();
        memberPriceBean.setStartDate(startDate);
        memberPriceBean.setEndDate(endDate);
        memberPriceBean.setCreateDoctor(getCurrentUser());
        List<MemberPrice> memberPriceList = memberPriceService.selectTrends(memberPriceBean);

        // 获取不同类型的priceTypeId
        PriceType priceType = new PriceType();
        priceType.setPriceTypeName("会员费用");
        String priceTypeIdVip = priceTypeService.selectParam(priceType).get(0).getId();
        priceType.setPriceTypeName("药品费用");
        String priceTypeIdDrug = priceTypeService.selectParam(priceType).get(0).getId();
        priceType.setPriceTypeName("诊疗费用");
        String priceTypeIdTherapy = priceTypeService.selectParam(priceType).get(0).getId();

        int priceVip = 0;
        int priceDrug = 0;
        int priceTherapy = 0;
        for (MemberPrice memberPrice : memberPriceList) {
            if (memberPrice.getPriceTypeId().equals(priceTypeIdVip)) {
                // 终结果为会员费用总价
                priceVip += Integer.parseInt(memberPrice.getPrice());
            } else if (memberPrice.getPriceTypeId().equals(priceTypeIdDrug)) {
                // 终结果为药品费用总价
                priceDrug += Integer.parseInt(memberPrice.getPrice());
            } else if (memberPrice.getPriceTypeId().equals(priceTypeIdTherapy)) {
                // 终结果为诊疗费用总价
                priceTherapy += Integer.parseInt(memberPrice.getPrice());
            } else {
                return requestUnsuccessful("请求失败");
            }
        }

        Map<String, String> intraDayData = new HashMap<>();
        intraDayData.put("amountContract", String.valueOf(amountContract));
        intraDayData.put("memberCount", String.valueOf(memberCount));
        intraDayData.put("priceVip", String.valueOf(priceVip));
        intraDayData.put("priceDrug", String.valueOf(priceDrug));
        intraDayData.put("priceTherapy", String.valueOf(priceTherapy));
        return requestArgumentError(intraDayData);
    }

    /**
     * 获取时间段内某项药品使用信息
     *
     * @param startTime
     * @param stopTime
     * @param drugId
     */
    @GetMapping(value = "cliptimedruguse")
    public Map clipTimeDrugUse(String startTime, String stopTime, String drugId) {

        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(stopTime) || StringUtils.isBlank(drugId)) {
            return requestArgumentEmpty("参数为空");
        }

        // 获取startTime-stopTime内所有memberPrice
        Date startDate = DateUtils.stringToDate2(startTime);
        Date endDate = DateUtils.stringToDate2(stopTime);

        MemberPriceBean memberPriceBean = new MemberPriceBean();
        memberPriceBean.setStartDate(startDate);
        memberPriceBean.setEndDate(endDate);
        memberPriceBean.setCreateDoctor(getCurrentUser());

        List<MemberPrice> memberPriceList = memberPriceService.selectTrends(memberPriceBean);
        if (memberPriceList.isEmpty() || memberPriceList == null) {
            return requestSelectFail("段时间药品使用查询memberPriceList失败");
        }

        // 获取处方id列表
        PriceType priceType = new PriceType();
        priceType.setPriceTypeName("药品费用");
        String priceTypeId = priceTypeService.selectParam(priceType).get(0).getId();
        if (StringUtils.isBlank(priceTypeId)) {
            return requestSelectFail("priceTypeName为'药品费用'的priceTypeId查询失败");
        }

        List<String> prescriptionIds = new ArrayList<>();
        for (MemberPrice memberPrice : memberPriceList) {
            if (memberPrice.getPriceTypeId().equals(priceTypeId)) {
                prescriptionIds.add(memberPrice.getOrderId());
            }
        }

        // 筛选出drug_id=drugId,且id in prescriptionIds的prescription
        PrescriptionBean prescriptionBean = new PrescriptionBean();
        prescriptionBean.setPrescriptionIds(prescriptionIds);
        prescriptionBean.setDrugId(drugId);
        List<Prescription> prescriptionList = prescriptionService.selectByIdListAndDrug(prescriptionBean);

        return requestSelectSuccess(prescriptionList);
    }

    /**
     * 段时间内使用各类药品的总价格
     *
     * @param startTime
     * @param stopTime
     */
    @GetMapping(value = "cliptimedrugprice")
    public Map clipTimeDrugPrice(String startTime, String stopTime) {

        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(stopTime)) {
            return requestArgumentEmpty("参数为空");
        }

        // 获取startTime-stopTime内所有memberPrice
        Date startDate = DateUtils.stringToDate2(startTime);
        Date endDate = DateUtils.stringToDate2(stopTime);
        MemberPriceBean memberPriceBean = new MemberPriceBean();
        memberPriceBean.setStartDate(startDate);
        memberPriceBean.setEndDate(endDate);
        memberPriceBean.setCreateDoctor(getCurrentUser());

        List<MemberPrice> memberPriceList = memberPriceService.selectTrends(memberPriceBean);
        if (memberPriceList.isEmpty() || memberPriceList == null) {
            return requestSelectFail("startTime,stopTime,createDoctor查询memberPriceList失败");
        }

        // 按照drugId,统计price
        PriceType priceType = new PriceType();
        priceType.setPriceTypeName("药品费用");
        String priceTypeId = priceTypeService.selectParam(priceType).get(0).getId();
        if (StringUtils.isBlank(priceTypeId)) {
            return requestSelectFail("priceTypeName为'药品费用'的priceTypeId查询失败");
        }

        Map<String, Map<String, String>> drugPrice = new LinkedHashMap<>();
        int i = 0;
        int price = 0;
        for (MemberPrice memberPrice : memberPriceList) {
            if (memberPrice.getPriceTypeId().equals(priceTypeId)) {

                //获取prescription的drugId
                Prescription prescription = prescriptionService.selectByPrimaryKey(memberPrice.getOrderId());
                String drugId = prescription.getDrugId();

                // drugId不在drugPrice中
                if (drugPrice.get(drugId) == null) {
                    price += Integer.parseInt(memberPrice.getPrice());
                    Map<String, String> inside = new HashMap<>();
                    inside.put("amount", String.valueOf(i++));
                    inside.put("price", String.valueOf(price));
                    drugPrice.put(drugId, inside);
                }
                // drugId存在drugPrice中
                else if (drugPrice.containsKey(drugId)) {
                    Map<String, String> inside = drugPrice.get(drugId);
                    String formerAmount = inside.get("amount");
                    String formerPrice = inside.get("price");
                    int newAmount = Integer.parseInt(formerAmount) + 1;
                    int newPrice = Integer.parseInt(memberPrice.getPrice()) + Integer.parseInt(formerPrice);
                    inside.put("amount", String.valueOf(newAmount));
                    inside.put("price", String.valueOf(newPrice));
                    drugPrice.put(drugId, inside);
                }
            }
        }

        return requestSuccessful(drugPrice);
    }

    /**
     * 出诊查询
     *
     * @return
     */
    @GetMapping(value = "outcall")
    public Map outCall(String startTime, String stopTime) {

        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(stopTime)) {
            return requestArgumentEmpty("参数为空");
        }

        Date startDate = DateUtils.stringToDate(startTime);
        Date endDate = DateUtils.stringToDate(stopTime);

        MemberPriceBean memberPriceBean = new MemberPriceBean();
        memberPriceBean.setStartDate(startDate);
        memberPriceBean.setEndDate(endDate);
        memberPriceBean.setCreateDoctor(getCurrentUser());

        List<MemberPrice> memberPriceList = memberPriceService.selectTrends(memberPriceBean);
        if (memberPriceList.isEmpty() || memberPriceBean == null) {
            return requestSelectFail("startDate,endDate,createDpctor查询memberList失败");
        }

        PriceType priceType = new PriceType();
        priceType.setPriceTypeName("会员费用");
        String priceTypeIdVip = priceTypeService.selectParam(priceType).get(0).getId();
        priceType.setPriceTypeName("诊疗费用");
        String priceTypeIdTherapy = priceTypeService.selectParam(priceType).get(0).getId();
        Produce produce = new Produce();
        produce.setProduceName("年费");
        String yearId = produceService.selectByName(produce);
        String yearPrice = produceService.selectById(yearId).getProducePrice();
        produce.setProduceName("普通");
        String singleId = produceService.selectByName(produce);
        String commonPrice = produceService.selectById(singleId).getProducePrice();

        Map<String, Map<String, String>> outCallDay = new LinkedHashMap<>();
        int yearSize = 0;  // 初始年费次数
        int singleSize = 0;  // 初始普通次数
        int outSize = 0;  // 初始出诊次数
        int price = 0;    // 初始出诊费用

        List<String> memberIds = new ArrayList<>();
        List<String> yearMember = new ArrayList<>();
        List<String> singleMember = new ArrayList<>();
        List<MemberPrice> memberPriceTherapyList = new ArrayList<>();

        // 会员费用,诊疗费用分类
        for (MemberPrice memberPrice : memberPriceList) {
            if (memberPrice.getPriceTypeId().equals(priceTypeIdVip)) {
                memberIds.add(memberPrice.getMemberId());
            } else if (memberPrice.getPriceTypeId().equals(priceTypeIdTherapy)) {
                memberPriceTherapyList.add(memberPrice);
                price += Integer.parseInt(memberPrice.getPrice());
                outSize++;
                if (outCallDay.get(memberPrice.getMemberId()) == null) {
                    Member member = new Member();
                    member.setId(memberPrice.getMemberId());
                    Member resMember = memberService.selectById(member);
                    Map<String, String> tempMap = new HashMap<String, String>();
                    tempMap.put("price", memberPrice.getPrice());
                    tempMap.put("number", "1");
                    tempMap.put("name", resMember.getMemberName());
                    tempMap.put("cardId", resMember.getMemberCard());
                    outCallDay.put(memberPrice.getMemberId(), tempMap);
                } else if (outCallDay.containsKey(memberPrice.getMemberId())) {
                    Map<String, String> tempMap = outCallDay.get(memberPrice.getMemberId());
                    double a = Double.parseDouble(tempMap.get("price"));
                    double b = Double.parseDouble(memberPrice.getPrice());
                    tempMap.put("price", String.valueOf(a + b));
                    int number = Integer.parseInt(tempMap.get("number"));
                    number++;
                    tempMap.put("number", String.valueOf(number));
                    outCallDay.put(memberPrice.getMemberId(), tempMap);
                }
            }
        }
        List<Map<String, String>> diagnosisListMap = new ArrayList<Map<String, String>>();
        for (Map<String, String> v : outCallDay.values()) {
            diagnosisListMap.add(v);
        }
        // 按年费yearMember,普通singleMember分类
        List<Contract> contractList = contractService.selectByMemberList(memberIds);
        for (Contract contract : contractList) {
            if (contract.getProduceId().equals(yearId)) {
                yearMember.add(contract.getMemberId());
            } else if (contract.getProduceId().equals(singleId)) {
                singleMember.add(contract.getMemberId());
            }
        }

        for (MemberPrice memberPrice : memberPriceList) {
            if (yearMember.contains(memberPrice.getMemberId())) {
                yearSize++;
            } else if (singleMember.contains(memberPrice.getMemberId())) {
                singleSize++;
            }
        }

        // 获取startDate,endDate之间的天数
        int countDay = DateUtils.daysBetween(startDate, endDate);
        int subsidy = countDay * 10;  // 补贴费用,按每天10元计算

/*        // memberPriceTherapyList诊疗费用列表每天筛选
        Map<String, String> dayPrice = new HashMap<>();
        for (int i = 0; i < countDay; i++) {
            for (MemberPrice memberPrice : memberPriceTherapyList) {
                Date memberPriceDate = memberPrice.getCreateTime();
                if (memberPriceDate.after(DateUtils.dayBegin(startDate)) && memberPriceDate.before(DateUtils.dayEnd(startDate))) {
                    dayPrice.put(String.valueOf(startDate), String.valueOf(price += Integer.parseInt(memberPrice.getPrice())));
                }
            }
            startDate = DateUtils.dayAddDay(startDate);
        }*/

        // 总费用为年费+普通+出诊+补贴
        int memberTotal = yearSize * Integer.parseInt(yearPrice);
        int diagnosisTotal = singleSize * Integer.parseInt(commonPrice);
        int totalPrice = memberTotal + diagnosisTotal + price + subsidy;

        DoctorOutCallBean doctorOutCallBean = new DoctorOutCallBean();
        doctorOutCallBean.setYearSize(String.valueOf(yearSize));
        doctorOutCallBean.setSingleSize(String.valueOf(singleSize));
        doctorOutCallBean.setOutSize(String.valueOf(outSize));
        doctorOutCallBean.setSubsidy(String.valueOf(subsidy));
        doctorOutCallBean.setTotalPrice(String.valueOf(totalPrice));
        doctorOutCallBean.setMemberTotal(String.valueOf(memberTotal));
        doctorOutCallBean.setDiagnosisTotal(String.valueOf(diagnosisTotal));
        doctorOutCallBean.setDiagnosisListMap(diagnosisListMap);

        return requestUnsuccessful(doctorOutCallBean);
    }

}