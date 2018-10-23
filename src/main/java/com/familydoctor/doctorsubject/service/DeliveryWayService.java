package com.familydoctor.doctorsubject.service;

import com.familydoctor.doctorsubject.entity.DeliveryWay;

import java.util.List;

/**
 * @author YoonaLT
 * @version 1.0
 * Running version:1.8
 * Functional Description: 给药
 */
public interface DeliveryWayService {

    /**
     * 添加给药信息
     *
     * @param record
     */
    int insertDelivery(DeliveryWay record);

    /**
     * 删除给药
     *
     * @param deliveryWay
     */
    int softDel(DeliveryWay deliveryWay);

    /**
     * 修改给药
     *
     * @param deliveryWay
     */
    int updateById(DeliveryWay deliveryWay);

    /**
     * 有Id查询给药
     *
     * @param deliveryWay
     */
    DeliveryWay selectById(DeliveryWay deliveryWay);

    /**
     * 查询所有给药
     *
     */
    List<DeliveryWay> selectAllDelivery();

    List<DeliveryWay> selectPage(DeliveryWay deliveryWay);
}
