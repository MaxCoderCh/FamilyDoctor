package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.Hospital;

import java.util.List;

public interface HospitalService {

    /**
     * 添加医院
     *
     * @param hospital
     */
    int addHospital(Hospital hospital);

    /**
     * 删除医院
     *
     * @param hospital
     */
    int softDel(Hospital hospital);

    /**
     * 修改医院
     *
     * @param hospital
     */
    int updateHospital(Hospital hospital);


    /**
     * 由Id查询医院
     *
     * @param hospital
     */
    Hospital selectHospital(Hospital hospital);

    /**
     * 查询所有医院
     *
     * @param
     */
    List<Hospital> selectAll(String pageNo, String pageSize);
}
