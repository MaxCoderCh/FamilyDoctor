package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.YoonaLTsUtils.DateUtils;
import com.familydoctor.doctorsubject.YoonaLTsUtils.IdentityCard;
import com.familydoctor.doctorsubject.entity.Member;
import com.familydoctor.doctorsubject.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Member
 */
@RestController
@RequestMapping(value = "familydoctor/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    /**
     * 添加Member
     *
     * @param member
     * @return requestInsertSuccess(member)
     */
    @PostMapping(value = "add")
    public Map addMemberMsg(Member member) {

        if (member == null || StringUtils.isBlank(member.getMemberCard())) {
            return requestArgumentEmpty("参数为空");
        }
        String card = member.getMemberCard();
        Date memberBirthday = DateUtils.stringToDate2(IdentityCard.getBirthByIdCard(card));
        String memberAge = String.valueOf(IdentityCard.getAgeByIdCard(card));
        String memberSex = IdentityCard.getGenderByIdCard(card);
        member.setMemberBirthday(memberBirthday);
        member.setMemberAge(memberAge);
        member.setMemberSex(memberSex);
        int i = memberService.add(member);
        if (i > 0) {
            return requestInsertSuccess(member);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 删除Member
     *
     * @param member
     * @return
     */
    @GetMapping(value = "softDel")
    public Map deleteMember(Member member) {

        if (StringUtils.isBlank(member.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        member.setDeleteTime(addTime());
        int i = memberService.softDel(member);
        if (i > 0) {
            return requestDeleteSuccess(member);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 更新Member
     *
     * @param member
     * @return requestUpdateSuccess(member)
     */
    @PostMapping(value = "update")
    public Map updateMember(Member member) {

        if (StringUtils.isBlank(member.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        member.setUpdateTime(addTime());
        int i = memberService.updateById(member);
        if (i > 0) {
            return requestUpdateSuccess(member);
        } else {
            return requestUpdateFail("更新失败");
        }

    }

    /**
     * 由Id查询Member
     *
     * @param member
     * @return requestSelectSuccess(resultMember)
     */
    @GetMapping(value = "selectById")
    public Map selectMemberById(Member member) {

        if (StringUtils.isBlank(member.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        Member resultMember = memberService.selectById(member);
        if (resultMember != null) {
            return requestSelectSuccess(resultMember);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 由身份证查询Member
     *
     * @param member
     * @return requestSelectSuccess(resultMember)
     */
    @GetMapping(value = "selectByCard")
    public Map selectMemberByCard(Member member) {

        if (StringUtils.isBlank(member.getMemberCard())) {
            return requestArgumentEmpty("参数为空");
        }

        Member resultMember = memberService.selectByCard(member);
        if (resultMember != null) {
            return requestSelectSuccess(resultMember);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 查询Member列表
     *
     * @return requestSelectSuccess(memberList)
     */
    @GetMapping(value = "selectAll")
    public Map selectAllMember() {

        List<Member> memberList = memberService.selectAll();
        if (memberList != null && !memberList.isEmpty()) {
            return requestSelectSuccess(memberList);
        } else {
            return requestSelectFail("查询失败");
        }
    }

    /**
     * 分页查询
     *
     * @param member
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(Member member) {

        List<Member> memberList = memberService.selectPage(member);

        if (memberList != null && !memberList.isEmpty()) {
            return requestSelectSuccess(memberList);
        }

        return requestSelectFail("查询失败");
    }
}
