package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.Drug;
import com.familydoctor.doctorsubject.mapper.DrugMapper;
import com.familydoctor.doctorsubject.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 药品
 */
@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugMapper drugMapper;

    /**
     * 添加药品
     *
     * @param drug
     * @return drugMapper.insert(drug)
     */
    @Override
    public int addDrug(Drug drug) {
        return drugMapper.insertSelective(drug);
    }

    /**
     * 删除药品
     *
     * @param drug
     * @return drugMapper.deleteByPrimaryKey(drug.getId ())
     */
    @Override
    public int softDel(Drug drug) {
        return drugMapper.softDelete(drug);
    }

    /**
     * 修改药品
     *
     * @param drug
     * @return drugMapper.updateByPrimaryKey(drug)
     */
    @Override
    public int updateDrug(Drug drug) {
        return drugMapper.updateByPrimaryKeySelective(drug);
    }

    /**
     * 根据Id查询对应的药品
     *
     * @param id
     * @return drugMapper.selectByPrimaryKey(drug.getId ())
     */
    @Override
    public Drug selectDrugById(String id) {
        return drugMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询药品列表
     *
     * @return drugMapper.selectAroundDrug(drug)
     */
    @Override
    public List<Drug> selectAllDrug(Drug drug) {

        return drugMapper.selectAroundDrug(drug);

    }
}
