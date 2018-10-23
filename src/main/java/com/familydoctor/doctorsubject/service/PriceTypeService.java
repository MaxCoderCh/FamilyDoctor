package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.PriceType;

import java.util.List;

public interface PriceTypeService {

    /**
     * 动态查询
     *
     * @param priceType
     */
    PriceType selectParam(PriceType priceType);

    /**
     * 添加缴费类型
     *
     * @param record
     */
    int insertSelective(PriceType record);

    /**
     * 删除缴费类型
     *
     * @param priceType
     */
    int softDeleteById(PriceType priceType);

    /**
     * 物理删除缴费类型
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     * 修改缴费类型
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PriceType record);

    List<PriceType> selectPage(PriceType priceType);


}
