package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.YoonaLTsUtils.DateUtils;
import com.familydoctor.doctorsubject.bean.ContractBean;
import com.familydoctor.doctorsubject.bean.MemberBean;
import com.familydoctor.doctorsubject.bean.MemberLableBean;
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
@RequestMapping(value = "familydoctor/memberLable")
public class MemberLableController extends BaseController {

    @Autowired
    private MemberLableServce memberLableServce;

    @Autowired
    private LableService lableService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProduceService produceService;

    @Autowired
    private MemberPriceService memberPriceService;

    /**
     * 由Member.Id查询MemberLable
     *
     * @param memberLable
     * @return requestSelectSuccess(resultMemberLable1)
     */
    @GetMapping(value = "selectbyid")
    public Map selectMemberLableByMember(MemberLable memberLable) {

        if (StringUtils.isBlank(memberLable.getMemberId())) {
            return requestArgumentEmpty("参数为空");
        }

        MemberLable resultMemberLable1 = memberLableServce.selectByMember(memberLable);
        if (resultMemberLable1 != null) {
            return requestSelectSuccess(resultMemberLable1);
        } else {
            return requestSelectFail("查询结果为空");
        }

    }

    /**
     * 修改MemberLable
     *
     * @param memberLable
     * @return requestUpdateSuccess(memberLable)
     */
    @PostMapping(value = "update")
    public Map updateMemberLableMsg(MemberLable memberLable) {

        if (memberLable == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = memberLableServce.updateMemberLable(memberLable);
        if (i > 0) {
            return requestUpdateSuccess(memberLable);
        } else {
            return requestUpdateFail("修改失败");

        }
    }

    /**
     * 按照lableName查询lableClassName为慢病标签的memberLable
     *
     * @param lableNames
     * @param lableNames
     */
    @GetMapping(value = "selectbyterm")
    public Map selectTerm(String[] lableNames) {

        if (lableNames == null) {
            return requestArgumentEmpty("标签名为空");
        }

        //查询所有符合lableNames的lable
        List<Lable> resultLable = lableService.selectByName(lableNames);
        if (resultLable == null || resultLable.isEmpty()) {
            return requestSelectFail("查询结果为空");
        }

        //筛选MemberLable中lableId的在resultLible中的所有MemberLable,并取出MemberId
        List<String> lableIdList = new ArrayList<>();
        for (Lable lable : resultLable) {
            lableIdList.add(lable.getId());
        }

        MemberLableBean memberLableBean = new MemberLableBean();
        memberLableBean.setLableIdList(lableIdList);
        List<MemberLable> memberLableList = memberLableServce.selectByList(memberLableBean);

        if (memberLableList == null || memberLableList.isEmpty()) {
            return requestSelectFail("查询MemberLable失败");
        }

        List<String> memberIdList = new ArrayList<>();
        for (MemberLable memberLable : memberLableList) {
            memberIdList.add(memberLable.getMemberId());
        }

        //查询出所有memberId在memberIdList中的member
        MemberBean memberBean = new MemberBean();
        memberBean.setMemberIdList(memberIdList);
        List<Member> memberList = memberService.selectByIdList(memberBean);
        if (memberList == null || memberList.isEmpty()) {
            return requestSelectFail("memberIdList查询MemberList失败");
        }

        return requestSelectSuccess(memberList);
    }

    /**
     * 段时间内签约统计
     *
     * @param startTime
     * @param stopTime
     */
    @GetMapping(value = "statistics")
    public Map signStatistics(String startTime, String stopTime) {

        startTime = startTime + " 00:00:00";
        stopTime = stopTime + " 23:59:59";
        Date startDate = DateUtils.stringToDate(startTime);
        Date endDate = DateUtils.stringToDate(stopTime);
        MemberLableBean memberLableBean = new MemberLableBean();

        //查询createUser为该使用者的且时间段处于startTime,stopTime的签约
        ContractBean contractBean = new ContractBean();
        contractBean.setStartDateL(startDate);
        contractBean.setEndDateL(endDate);
        contractBean.setCreateUser(getCurrentUser());

        List<Contract> contractList = contractService.selectTrends(contractBean);
        if (contractList.isEmpty() || contractList == null) {
            return requestSelectFail("startTime,stopTime,CreateUser查询Contract失败");
        }

        //memberLableBean属性TotalSize赋值
        String totalSize = String.valueOf(contractList.size());
        memberLableBean.setTotalSize(totalSize);

        //合约属性区分
        Produce produce = new Produce();
        produce.setProduceName("年费");
        String produceYearId = produceService.selectByName(produce);
        produce.setProduceName("普通");
        String produceCommonId = produceService.selectByName(produce);

        //按日期去重集合
        Map<String, Map<String, String>> yearMap = new LinkedHashMap<>();
        Map<String, Map<String, String>> commonMap = new LinkedHashMap<>();

        int i = 0;
        int j = 0;
        int k = 1;

        List<String> memberIdList = new ArrayList<>();
        List<String> yearMemberId = new ArrayList<>();
        List<String> commonMemberId = new ArrayList<>();
        for (Contract contract : contractList) {
            memberIdList.add(contract.getMemberId());
            System.out.println(contract.getProduceId());
            System.out.println(produceYearId);
            if (contract.getProduceId().equals(produceYearId)) {
                i++;
                yearMemberId.add(contract.getMemberId());
                //年费日期去重
                if (yearMap.get(DateUtils.dateToString(contract.getCreateTime())) == null) {
                    Map<String, String> yearInside = new LinkedHashMap<>();
                    yearInside.put("date", DateUtils.dateToString(contract.getCreateTime()));
                    yearInside.put("yearSize", "1");
                    yearMap.put(DateUtils.dateToString(contract.getCreateTime()), yearInside);

                } else {
                    k++;
                    Map<String, String> yearInside = new LinkedHashMap<>();
                    yearInside.put("date", DateUtils.dateToString(contract.getCreateTime()));
                    yearInside.put("yearSize", String.valueOf(k));
                    yearMap.put(DateUtils.dateToString(contract.getCreateTime()), yearInside);
                }

            } else if (contract.getProduceId().equals(produceCommonId)) {
                commonMemberId.add(contract.getMemberId());
                j++;
                //普通日期去重
                if (commonMap.get(DateUtils.dateToString(contract.getCreateTime())) == null) {
                    Map<String, String> commonInside = new LinkedHashMap<>();
                    commonInside.put("date", DateUtils.dateToString(contract.getCreateTime()));
                    commonInside.put("commonSize", "1");
                    commonMap.put(DateUtils.dateToString(contract.getCreateTime()), commonInside);
                } else {
                    k++;
                    Map<String, String> commonInside = new LinkedHashMap<>();
                    commonInside.put("date", DateUtils.dateToString(contract.getCreateTime()));
                    commonInside.put("commonSize", String.valueOf(k));
                    commonMap.put(DateUtils.dateToString(contract.getCreateTime()), commonInside);
                }
            }
        }

        MemberBean memberBean = new MemberBean();
        //memberLableBean属性YearMemberList赋值
        if (yearMemberId.size() > 0) {
            memberBean.setMemberIdList(yearMemberId);
            List<Member> yearMemberList = memberService.selectByIdList(memberBean);
            memberLableBean.setYearMemberList(yearMemberList);
        }

        //memberLableBean属性CommonMemberList赋值
        if (commonMemberId.size() > 0) {
            memberBean.setMemberIdList(commonMemberId);
            List<Member> commonMemberList = memberService.selectByIdList(memberBean);
            memberLableBean.setCommonMemberList(commonMemberList);
        }

        //memberLableBean属性YearMap赋值
        List<Map<String, String>> resYearList = new LinkedList<>();
        for (String key : yearMap.keySet()) {
            resYearList.add(yearMap.get(key));
        }
        memberLableBean.setYearMap(resYearList);

        //memberLableBean属性CommonMap赋值
        List<Map<String, String>> resCommonList = new LinkedList<>();
        for (String key : commonMap.keySet()) {
            resCommonList.add(commonMap.get(key));
        }
        memberLableBean.setCommonMap(resCommonList);

        //memberLableBean属性YearSize,SingleSize赋值
        String yearSize = String.valueOf(i);
        memberLableBean.setYearSize(yearSize);
        String singleSize = String.valueOf(j);
        memberLableBean.setSingleSize(singleSize);

        //memberLableBean属性TotalPrice赋值
        List<String> priceList = memberPriceService.selectPriceByMemberIdList(memberIdList);
        if (priceList.isEmpty() || priceList == null) {
            return requestSelectFail("memberIdList查询price失败");
        }
        String totalPrice;
        int intNum = 0;
        for (String price : priceList) {
            intNum += Integer.parseInt(price);
        }
        totalPrice = String.valueOf(intNum);
        memberLableBean.setTotalPrice(totalPrice);

        return requestSelectSuccess(memberLableBean);

    }

}
