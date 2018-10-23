package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.YoonaLTsUtils.DateUtils;
import com.familydoctor.doctorsubject.YoonaLTsUtils.IdentityCard;
import com.familydoctor.doctorsubject.entity.*;
import com.familydoctor.doctorsubject.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 签约
 */
@RestController
@RequestMapping(value = "familydoctor/sign")
public class SignController extends BaseController {

    @Autowired
    private ProduceService produceService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private MemberPriceService memberPriceService;

    @Autowired
    private LableService lableService;

    @Autowired
    private LableClassService lableClassService;

    @Autowired
    private MemberLableServce memberLableService;

    @Autowired
    private PriceTypeService priceTypeService;

    /**
     * 签约新的会员
     *
     * @param produceId
     * @param member
     */
    @PostMapping(value = "add")
    public Map signNewMember(String produceId, Member member) {

        //参数校验
        if (StringUtils.isBlank(member.getMemberCard()) || StringUtils.isBlank(produceId)) {
            return requestArgumentEmpty("参数为空");
        }

        //参数校验-身份证,
        if (!IdentityCard.validateCard(member.getMemberCard())) {
            return requestArgumentError("身份证输入有误!");
        }
        member = memberService.selectByCard(member);
        //参数获取
        Produce produce = produceService.selectById(produceId);
        Date date = new Date();

        //由传入的会员身份证获取该会员年龄,性别,生日.
        Integer age = IdentityCard.getAgeByIdCard(member.getMemberCard());
        String sex = IdentityCard.getGenderByIdCard(member.getMemberCard());
        String bir = IdentityCard.getBirthByIdCard(member.getMemberCard());

        //保存会员信息
        member.setCreateDoctor(getCurrentUser());
        member.setUpdateDoctor(getCurrentUser());
        member.setCreateTime(addTime());
        member.setUpdateTime(addTime());
        member.setMemberSex(sex);
        member.setMemberAge(age.toString());
        member.setMemberBirthday(DateUtils.stringToDate(bir));

        int i = memberService.updateById(member);
        if (i <= 0) {
            return requestInsertFail("添加member失败.");
        }

        //添加合约信息
        Contract contract = new Contract();
        contract.setMemberId(member.getId());
        contract.setProduceId(produceId);
        contract.setCreateUser(getCurrentUser());
        contract.setUpdateUser(getCurrentUser());
        contract.setBeginDate(addTime());
        contract.setEndDate(DateUtils.nowTimeYearLater());

        int j = contractService.insertContract(contract);
        if (j <= 0) {
            return requestInsertFail("添加contract失败.");
        }

        //添加患者缴费信息
        MemberPrice memberPrice = new MemberPrice();
        PriceType priceType = new PriceType();
        priceType.setPriceTypeNumber("00003");
        memberPrice.setMemberId(member.getId());
        memberPrice.setCreateDoctor(getCurrentUser());
        memberPrice.setUpdateDoctor(getCurrentUser());
        memberPrice.setCreateTime(addTime());
        memberPrice.setUpdateTime(addTime());
        memberPrice.setPrice(produce.getProducePrice());
        memberPrice.setIspay("1");
        memberPrice.setOrderId(produce.getId());
        memberPrice.setPriceTypeId(priceTypeService.selectParam(priceType).getId());
        int n = memberPriceService.addMemberPrice(memberPrice);
        if (n <= 0) {
            return requestInsertFail("添加memberPrice失败");
        }

        //创建会员标签
        List<Lable> lableList = lableService.selectAll();

        for (Lable lable : lableList) {
            MemberLable memberLable = new MemberLable();
            memberLable.setMemberId(member.getId());
            memberLable.setCreateDoctor(getCurrentUser());
            memberLable.setUpdateDoctor(getCurrentUser());
            memberLable.setCreateTime(addTime());
            memberLable.setUpdateTime(addTime());

            String lableClassName = (lableClassService.selectById(lable.getLableClassId())).getClassName();
            System.out.println(lableClassName);

            if (lableClassName.equals("性别标签")) {
                if (sex.equals(lable.getLableName())) {
                    memberLable.setShowView("4");
                    memberLable.setLableId(lable.getId());
                    int o = memberLableService.insertSelect(memberLable);
                }
            } else if (lableClassName.equals("会员标签")) {
                if (produce.getProduceName().equals(lable.getLableName())) {
                    memberLable.setShowView("3");
                    memberLable.setLableId(lable.getId());
                    int o = memberLableService.insertSelect(memberLable);
                }

            } else if (lableClassName.equals("关注标签")) {
                memberLable.setShowView("0");
                memberLable.setLableId(lable.getId());
                int o = memberLableService.insertSelect(memberLable);
            } else if (lableClassName.equals("慢病标签")) {
                memberLable.setShowView("0");
                memberLable.setLableId(lable.getId());
                int o = memberLableService.insertSelect(memberLable);
            }

        }
        return requestInsertFail("添加memberLable失败");
    }

}
