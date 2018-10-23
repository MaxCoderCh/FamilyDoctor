package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Cases;
import com.familydoctor.doctorsubject.entity.CasesDisease;
import com.familydoctor.doctorsubject.mapper.CasesMapper;
import com.familydoctor.doctorsubject.service.CasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasesServiceImpl implements CasesService {

    @Autowired
    private CasesMapper casesMapper;

    /**
     * 添加病历信息
     *
     * @param cases
     * @return casesMapper.insert(cases)
     */
    @Override
    public int addCases(Cases cases) {
        return casesMapper.insertSelective(cases);
    }

    /**
     * 修改病历信息
     *
     * @param cases
     * @return casesMapper.updateByPrimaryKey(cases)
     */
    @Override
    public int updateCases(Cases cases) {
        return casesMapper.updateByPrimaryKeySelective(cases);
    }

    /**
     * 由会员ID查询病历
     *
     * @param string
     * @return casesMapper.selectByPrimaryKey(string)
     */
    @Override
    public List<Cases> selectCasesMember(String string) {
        return casesMapper.selectCasesByMember(string);
    }

    /**
     * 由Cases.Id查询对应的病历信息
     *
     * @param string
     * @return casesMapper.selectByPrimaryKey(string)
     */
    @Override
    public Cases selectById(String string) {
        return casesMapper.selectByPrimaryKey(string);
    }

    @Override
    public List<Cases> selectPage(Cases cases) {
        return casesMapper.selectPage(cases);
    }

    @Override
    public int delAll() {
        return casesMapper.deleteAll();
    }
}
