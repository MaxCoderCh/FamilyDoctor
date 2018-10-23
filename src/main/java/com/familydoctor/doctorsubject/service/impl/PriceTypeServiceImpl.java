package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.PriceType;
import com.familydoctor.doctorsubject.mapper.PriceTypeMapper;
import com.familydoctor.doctorsubject.service.PriceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceTypeServiceImpl implements PriceTypeService {

    @Autowired
    private PriceTypeMapper priceTypeMapper;

    /**
     * 动态查询
     *
     * @param priceType
     * @return priceTypeMapper.selectTrends(priceType)
     */
    @Override
    public PriceType selectParam(PriceType priceType) {
        return priceTypeMapper.selectTrends(priceType);
    }

    /**
     * 添加缴费类型
     *
     * @param record
     * @return priceTypeMapper.insertSelective(record) 返回值为int
     */
    @Override
    public int insertSelective(PriceType record) {
        return priceTypeMapper.insertSelective(record);
    }

    /**
     * 删除缴费类型
     *
     * @param priceType
     * @return
     */
    @Override
    public int softDeleteById(PriceType priceType) {
        return priceTypeMapper.softDeleteById(priceType);
    }

    /**
     * 物理删除缴费类型
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return priceTypeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改缴费类型
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(PriceType record) {
        return priceTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<PriceType> selectPage(PriceType priceType) {
        return priceTypeMapper.selectPage(priceType);
    }

}
