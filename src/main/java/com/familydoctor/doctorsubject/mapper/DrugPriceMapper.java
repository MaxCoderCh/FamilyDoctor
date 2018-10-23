package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.DrugPrice;

import java.util.List;

public interface DrugPriceMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(DrugPrice drugPrice);

    int insert(DrugPrice record);

    int insertSelective(DrugPrice record);

    DrugPrice selectByPrimaryKey(String id);

    DrugPrice selectTrends(DrugPrice drugPrice);

    List<DrugPrice> selectRoundPrice();

    List<DrugPrice> selectPage(DrugPrice drugPrice);

    int updateByPrimaryKeySelective(DrugPrice record);

    int updateByPrimaryKey(DrugPrice record);
}