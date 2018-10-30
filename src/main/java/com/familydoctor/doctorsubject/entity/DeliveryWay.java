package com.familydoctor.doctorsubject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

