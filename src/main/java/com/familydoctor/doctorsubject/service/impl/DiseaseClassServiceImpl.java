package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.DiseaseClass;
import com.familydoctor.doctorsubject.mapper.DiseaseClassMapper;
import com.familydoctor.doctorsubject.service.DiseaseClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 疾病所属类别
 */
@Service
public class DiseaseClassServiceImpl implements DiseaseClassService {

    @Autowired
    private DiseaseClassMapper diseaseClassMapper;

    /**
     * 添加疾病类别
     *
     * @param diseaseClass
     * @returndiseaseClassMapper.insert(diseaseClass)
     */
    @Override
    public int insertDiseaseClsass(DiseaseClass diseaseClass) {
        return diseaseClassMapper.insertSelective(diseaseClass);
    }

    /**
     * 修改疾病类别
     *
     * @param diseaseClass
     * @return diseaseClassMapper.updateByPrimaryKey(diseaseClass)
     */
    @Override
    public int updateById(DiseaseClass diseaseClass) {
        return diseaseClassMapper.updateByPrimaryKeySelective(diseaseClass);
    }

    /**
     * 删除疾病类别
     *
     * @param diseaseClass
     * @return diseaseClassMapper.deleteByPrimaryKey(diseaseClass.getId ())
     */
    @Override
    public int softDel(DiseaseClass diseaseClass) {
        return diseaseClassMapper.softDelete(diseaseClass);
    }

    @Override
    public List<DiseaseClass> selectAll() {
        return diseaseClassMapper.selectRound();
    }

    @Override
    public List<DiseaseClass> selectPage(DiseaseClass diseaseClass) {
        return diseaseClassMapper.selectPage(diseaseClass);
    }
}
