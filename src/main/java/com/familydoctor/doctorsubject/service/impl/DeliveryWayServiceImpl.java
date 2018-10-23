package com.familydoctor.doctorsubject.service.impl;

import com.familydoctor.doctorsubject.entity.DeliveryWay;
import com.familydoctor.doctorsubject.mapper.DeliveryWayMapper;
import com.familydoctor.doctorsubject.service.DeliveryWayService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YoonaLT
 * @version 1.0
 * Running version:1.8
 * Functional Description: 给药
 */
@Service
public class DeliveryWayServiceImpl implements DeliveryWayService {
    @Autowired
    private DeliveryWayMapper deliveryWayMapper;

    /**
     * 添加给药
     *
     * @param deliveryWay
     * @return deliveryWayMapper.insert(deliveryWay)
     */
    @Override
    public int insertDelivery(DeliveryWay deliveryWay) {
        return deliveryWayMapper.insertSelective(deliveryWay);
    }

    /**
     * 删除给药
     *
     * @param deliveryWay
     */
    @Override
    public int softDel(DeliveryWay deliveryWay) {
        return deliveryWayMapper.softDelete(deliveryWay);
    }

    /**
     * 修改给药
     *
     * @param deliveryWay
     */
    @Override
    public int updateById(DeliveryWay deliveryWay) {
        return deliveryWayMapper.updateByPrimaryKeySelective(deliveryWay);
    }

    /**
     * 有Id查询给药
     *
     * @param deliveryWay
     */
    @Override
    public DeliveryWay selectById(DeliveryWay deliveryWay) {
        return deliveryWayMapper.selectByPrimaryKey(deliveryWay.getId());
    }

    /**
     * 查询所有给药
     */
    @Override
    public List<DeliveryWay> selectAllDelivery() {
        return deliveryWayMapper.selectAll();
    }

    @Override
    public List<DeliveryWay> selectPage(DeliveryWay deliveryWay) {
        return deliveryWayMapper.selectPage(deliveryWay);
    }
}
