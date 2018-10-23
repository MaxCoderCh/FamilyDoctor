package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Disease;
import com.familydoctor.doctorsubject.mapper.DiseaseMapper;
import com.familydoctor.doctorsubject.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseMapper diseaseMapper;

    /**
     * 添加疾病信息
     *
     * @param disease
     * @return
     */
    @Override
    public int addDisease(Disease disease) {
        return diseaseMapper.insertSelective(disease);
    }

    /**
     * 修改疾病信息
     *
     * @param disease
     * @return
     */
    @Override
    public int updateDisease(Disease disease) {
        return diseaseMapper.updateByPrimaryKeySelective(disease);
    }

    /**
     * 删除纪斌发信息
     *
     * @param disease
     * @return
     */
    @Override
    public int softDel(Disease disease) {
        return diseaseMapper.softDelete(disease);
    }

    /**
     * 查询所有疾病列表
     */
    @Override
    public List<Disease> selectAll() {
        return diseaseMapper.selectRound();
    }

    @Override
    public List<Disease> selectPage(Disease disease) {
        return diseaseMapper.selectPage(disease);
    }
}
