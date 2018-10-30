package com.familydoctor.doctorsubject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Examination extends BaseEntity {
    private String id;

    private String height;

    private String weight;

    private String bmi;

    private String temperature;

    private String highPressure;

    private String lowPressure;

    private String afterBloodSugar;

    private String beforBloodSugar;

    private String pulse;

    private String memberId;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private String createDoctor;

    private String updateDoctor;

    private String deleteDoctor;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi == null ? null : bmi.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(String highPressure) {
        this.highPressure = highPressure == null ? null : highPressure.trim();
    }

    public String getLowPressure() {
        return lowPressure;
    }

    public void setLowPressure(String lowPressure) {
        this.lowPressure = lowPressure == null ? null : lowPressure.trim();
    }

    public String getAfterBloodSugar() {
        return afterBloodSugar;
    }

    public void setAfterBloodSugar(String afterBloodSugar) {
        this.afterBloodSugar = afterBloodSugar == null ? null : afterBloodSugar.trim();
    }

    public String getBeforBloodSugar() {
        return beforBloodSugar;
    }

    public void setBeforBloodSugar(String beforBloodSugar) {
        this.beforBloodSugar = beforBloodSugar == null ? null : beforBloodSugar.trim();
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse == null ? null : pulse.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getCreateDoctor() {
        return createDoctor;
    }

    public void setCreateDoctor(String createDoctor) {
        this.createDoctor = createDoctor == null ? null : createDoctor.trim();
    }

    public String getUpdateDoctor() {
        return updateDoctor;
    }

    public void setUpdateDoctor(String updateDoctor) {
        this.updateDoctor = updateDoctor == null ? null : updateDoctor.trim();
    }

    public String getDeleteDoctor() {
        return deleteDoctor;
    }

    public void setDeleteDoctor(String deleteDoctor) {
        this.deleteDoctor = deleteDoctor == null ? null : deleteDoctor.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}