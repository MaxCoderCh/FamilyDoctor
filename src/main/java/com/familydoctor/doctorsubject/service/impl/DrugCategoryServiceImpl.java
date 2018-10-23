package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.DrugCategory;
import com.familydoctor.doctorsubject.mapper.DrugCategoryMapper;
import com.familydoctor.doctorsubject.service.DrugCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 药品类别
 */
@Service
public class DrugCategoryServiceImpl implements DrugCategoryService {

    @Autowired
    private DrugCategoryMapper drugCategoryMapper;

    /**
     * 添加药品类别
     *
     * @param drugCategory
     * @return drugCategoryMapper.insert(drugCategory)
     */
    @Override
    public int addDrugCategory(DrugCategory drugCategory) {
        return drugCategoryMapper.insertSelective(drugCategory);
    }

    /**
     * 修改药品类别
     *
     * @param drugCategory
     * @return drugCategoryMapper.updateByPrimaryKey(drugCategory)
     */
    @Override
    public int updateDrugCategory(DrugCategory drugCategory) {
        return drugCategoryMapper.updateByPrimaryKeySelective(drugCategory);
    }

    /**
     * 删除药品类别
     *
     * @param drugCategory
     * @return drugCategoryMapper.deleteByPrimaryKey(drugCategory.getId ())
     */
    @Override
    public int softDel(DrugCategory drugCategory) {
        return drugCategoryMapper.softDelete(drugCategory);
    }

    /**
     * 由Id查询药品类别信息
     *
     * @param drugCategory
     * @return drugCategoryMapper.selectByPrimaryKey(drugCategory.getId ())
     */
    @Override
    public DrugCategory selectDrugCategory(DrugCategory drugCategory) {
        return drugCategoryMapper.selectByPrimaryKey(drugCategory.getId());
    }

    /**
     * 查询所有药品类别信息
     *
     * @return drugCategoryMapper.selectAll(drugCategory)
     */
    @Override
    public List<DrugCategory> selectAllMsg() {
        return drugCategoryMapper.selectAll();
    }

    @Override
    public List<DrugCategory> selectPage(DrugCategory drugCategory) {
        return drugCategoryMapper.selectPage(drugCategory);
    }
}
