package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.MemberLable;
import lombok.Data;

import java.util.List;

@Data
public class MemberLableBean extends MemberLable {

    private List<String> lableIdList;
}
