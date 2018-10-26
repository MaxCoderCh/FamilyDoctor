package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Member;
import com.familydoctor.doctorsubject.entity.MemberLable;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MemberLableBean extends MemberLable {

    private List<String> lableIdList; //标签类别列表

    private String yearSize; //年费数量

    private String singleSize; //单次数量

    private String totalSize; //总签约

    private String totalPrice; //总价格

    private List<Member> yearMemberList; //年费会员详细信息(memberName memberCard memberPrice)

    private List<Member> commonMemberList; //普通会员详细信息(memberName memberCard memberPrice)

    private List<Map<String, String>> yearMap; //按创建日期,每日签约的年费合约的信息集合

    private List<Map<String, String>> commonMap; //按创建日期,每日签约的普通合约的信息集合

    List<Map<String, String>> signPriceMap; //签约费
}
