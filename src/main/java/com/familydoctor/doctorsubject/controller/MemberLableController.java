package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.bean.MemberLableBean;
import com.familydoctor.doctorsubject.entity.Lable;
import com.familydoctor.doctorsubject.entity.MemberLable;
import com.familydoctor.doctorsubject.service.LableService;
import com.familydoctor.doctorsubject.service.MemberLableServce;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "familydoctor/memberLable")
public class MemberLableController extends BaseController {

    @Autowired
    private MemberLableServce memberLableServce;

    @Autowired
    private LableService lableService;

    /**
     * 由Member.Id查询MemberLable
     *
     * @param memberLable
     * @return requestSelectSuccess(resultMemberLable1)
     */
    @GetMapping(value = "selectById")
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
     * 标签条件查询
     *
     * @param memberLable
     * @param lableNames
     */
    @GetMapping(value = "selectByTerm")
    public Map selectTerm(MemberLable memberLable, String[] lableNames) {

        //由传入的lableName数组查询对应的lableId列表
        if (lableNames == null) {
            return requestArgumentEmpty("标签名为空");
        }

        List<Lable> resultLable = lableService.selectByName(lableNames);
        if (resultLable == null || resultLable.isEmpty()) {
            return requestSelectFail("查询结果为空");
        }

        List<String> lableIdList = new ArrayList<>();
        for (Lable lable : resultLable) {
            lableIdList.add(lable.getId());
        }

        MemberLableBean memberLableBean = new MemberLableBean();
        memberLableBean.setLableIdList(lableIdList);
        //查询lableId在lableIdList中的MemberLable
        List<MemberLable> memberLableList = memberLableServce.selectByList(memberLableBean);

        if (memberLableList == null || memberLableList.isEmpty()) {
            return requestSelectFail("查询MemberLable失败");
        }

        return requestSelectSuccess(memberLableList);
    }

    //签约统计
    @GetMapping(value = "statistics")
    public Map signStatistics() {
        return null;
    }

}
