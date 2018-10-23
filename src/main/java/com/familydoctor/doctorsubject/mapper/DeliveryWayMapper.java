package com.familydoctor.doctorsubject.mapper;

import com.familydoctor.doctorsubject.entity.DeliveryWay;

import java.util.List;

public interface DeliveryWayMapper {
    int deleteByPrimaryKey(String id);

    int softDelete(DeliveryWay deliveryWay);

    int insert(DeliveryWay record);

    int insertSelective(DeliveryWay record);

    DeliveryWay selectByPrimaryKey(String id);

    //无Id查询会员所有给药信息
    List<DeliveryWay> selectAll();

    List<DeliveryWay> selectPage(DeliveryWay deliveryWay);

    int updateByPrimaryKeySelective(DeliveryWay record);

    int updateByPrimaryKey(DeliveryWay record);
}