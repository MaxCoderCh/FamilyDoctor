package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.DrugPrice;

import java.util.List;

public interface DrugPriceService {

    /**
     * 添加药品价格
     *
     * @param record
     */
    int addDrugPrice(DrugPrice record);

    /**
     * 删除药品价格
     *
     * @param drugPrice
     */
    int softDel(DrugPrice drugPrice);

    /**
     * 修改价格
     *
     * @param drugPrice
     */
    int updateDp(DrugPrice drugPrice);

    /**
     * 由Id查询药品价格
     *
     * @param string
     */
    DrugPrice selectById(String string);

    /**
     * 查询所有药品价格信息
     */
    List<DrugPrice> selectAllPrice();

    /**
     * 动态查询
     * @param drugPrice
     */
    List<DrugPrice> getByParam(DrugPrice drugPrice);

    List<DrugPrice> selectPage(DrugPrice drugPrice);
}
