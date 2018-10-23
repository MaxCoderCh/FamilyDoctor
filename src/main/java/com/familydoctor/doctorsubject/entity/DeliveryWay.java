package com.familydoctor.doctorsubject.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DeliveryWay extends BaseEntity {
    private String id;

    private String deliveryWeyName;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private String createUser;

    private String updateUser;

    private String deleteUser;

    private String delFlag;}

