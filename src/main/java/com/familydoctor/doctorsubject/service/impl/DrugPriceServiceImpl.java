package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.DrugPrice;
import com.familydoctor.doctorsubject.mapper.DrugPriceMapper;
import com.familydoctor.doctorsubject.service.DrugPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugPriceServiceImpl implements DrugPriceService {

    @Autowired
    private DrugPriceMapper drugPriceMapper;

    /**
     * 添加药品价格
     *
     * @param record
     * @return drugPriceMapper.insert(record)
     */
    @Override
    public int addDrugPrice(DrugPrice record) {
        return drugPriceMapper.insertSelective(record);
    }

    /**
     * 删除药品价格
     *
     * @param drugPrice
     * @return drugPriceMapper.softDelete(drugPrice.getId ())
     */
    @Override
    public int softDel(DrugPrice drugPrice) {
        return drugPriceMapper.softDelete(drugPrice);
    }

    /**
     * 更新药品价格
     *
     * @param drugPrice
     * @return drugPriceMapper.updateByPrimaryKey(drugPrice)
     */
    @Override
    public int updateDp(DrugPrice drugPrice) {
        return drugPriceMapper.updateByPrimaryKeySelective(drugPrice);
    }

    /**
     * 由Id查询药品价格
     *
     * @param string
     * @return drugPriceMapper.selectByPrimaryKey(drugPrice.getId ())
     */
    @Override
    public DrugPrice selectById(String string) {
        return drugPriceMapper.selectByPrimaryKey(string);
    }

    /**
     * 查询所有药品价格
     *
     * @return drugPriceMapper.selectAoundPrice()
     */
    @Override
    public List<DrugPrice> selectAllPrice() {
        return drugPriceMapper.selectRoundPrice();
    }

    /**
     * 动态查询
     *
     * @param drugPrice
     * @return drugPriceMapper.selectTrends(drugPrice)
     */
    @Override
    public DrugPrice getByParam(DrugPrice drugPrice) {
        return drugPriceMapper.selectTrends(drugPrice);
    }

    @Override
    public List<DrugPrice> selectPage(DrugPrice drugPrice) {
        return drugPriceMapper.selectPage(drugPrice);
    }
}
