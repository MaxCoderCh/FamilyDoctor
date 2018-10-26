package com.familydoctor.doctorsubject.bean;

import com.familydoctor.doctorsubject.entity.Member;
import lombok.Data;

import java.util.List;

@Data
public class MemberBean extends Member {

    private List<String> memberIdList;
}
