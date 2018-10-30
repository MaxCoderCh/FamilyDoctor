package com.familydoctor.doctorsubject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Drug extends BaseEntity {
    private String id;

    private String drugCode;

    private String drugNameChina;

    private String drugNamePinyin;

    private String drugNameEnglish;

    private String drugPackage;

    private String drugStand;

    private String drugSmallestUnit;

    private String deliveryWayId;

    private String usageId;

    private String drugCategoryId;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private String createUser;

    private String updateUser;

    private String deleteUser;

    private String delFlag;

    private String drugComposition;

    private String drugCharacter;

    private String drugIndications;

    private String drugUsages;

    private String drugAdverseReactions;

    private String drugTaboo;

    private String drugAttention;

    private String drugPregnant;

    private String drugChildren;

    private String drugElderly;

    private String drugInteractions;

    private String drugOverdose;

    private String drugClinicalTrials;

    private String drugPharmacologyToxicology;

    private String drugPharmacokinetic;

    private String drugPeriodValidity;

    private String drugExecutionStandard;

    private String drugApprovalNumber;

    private String drugEnterprise;

    private String drugStorage;


}