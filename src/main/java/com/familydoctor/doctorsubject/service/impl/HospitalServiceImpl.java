package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Hospital;
import com.familydoctor.doctorsubject.mapper.HospitalMapper;
import com.familydoctor.doctorsubject.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;

    /**
     * 添加医院
     *
     * @param hospital
     * @return hospitalMapper.insert(hospital)
     */
    @Override
    public int addHospital(Hospital hospital) {
        return hospitalMapper.insertSelective(hospital);
    }

    /**
     * 逻辑删除医院
     *
     * @param hospital
     * @return hospitalMapper.softDelete(hospital.getId ())
     */
    @Override
    public int softDel(Hospital hospital) {
        return hospitalMapper.softDelete(hospital);
    }

    /**
     * 更新医院信息
     *
     * @param hospital
     * @return
     */
    @Override
    public int updateHospital(Hospital hospital) {
        return hospitalMapper.updateByPrimaryKeySelective(hospital);
    }

    /**
     * 由Id查询医院
     *
     * @param hospital
     * @return hospitalMapper.selectByPrimaryKey(hospital.getId ())
     */
    @Override
    public Hospital selectHospital(Hospital hospital) {
        return hospitalMapper.selectByPrimaryKey(hospital.getId());
    }

    /**
     * 查询所有医院
     *
     * @return
     */
    @Override
    public List<Hospital> selectAll(String pageNo, String pageSize) {
        return hospitalMapper.selectRound(pageNo, pageSize);
    }
}
