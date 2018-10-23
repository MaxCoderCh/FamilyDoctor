package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.PriceType;

import java.util.List;

public interface PriceTypeMapper {
    int deleteByPrimaryKey(String id);

    int softDeleteById(PriceType priceType);

    PriceType selectTrends(PriceType priceType);

    int insert(PriceType record);

    int insertSelective(PriceType record);

    PriceType selectByPrimaryKey(String id);

    List<PriceType> selectPage(PriceType priceType);

    int updateByPrimaryKeySelective(PriceType record);

    int updateByPrimaryKey(PriceType record);
}