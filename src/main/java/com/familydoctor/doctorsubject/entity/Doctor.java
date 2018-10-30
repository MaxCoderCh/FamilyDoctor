package com.familydoctor.doctorsubject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor extends BaseEntity {
    private String id;

    private String username;

    private String password;

    private String salt;

    private Boolean locked;

    private String doctorName;

    private String doctorCard;

    private String doctorOccupationalNo;

    private String branchId;

    private String doctorAge;

    private String doctorGrade;

    private String doctorAdd;

    private String doctorInfoadd;

    private String doctorBirthday;

    private String doctorSex;

    private String doctorInfo;

    private String doctorGood;

    private String hospitalId;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private String createUser;

    private String updateUser;

    private String deleteUser;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getDoctorCard() {
        return doctorCard;
    }

    public void setDoctorCard(String doctorCard) {
        this.doctorCard = doctorCard == null ? null : doctorCard.trim();
    }

    public String getDoctorOccupationalNo() {
        return doctorOccupationalNo;
    }

    public void setDoctorOccupationalNo(String doctorOccupationalNo) {
        this.doctorOccupationalNo = doctorOccupationalNo == null ? null : doctorOccupationalNo.trim();
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    public String getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(String doctorAge) {
        this.doctorAge = doctorAge == null ? null : doctorAge.trim();
    }

    public String getDoctorGrade() {
        return doctorGrade;
    }

    public void setDoctorGrade(String doctorGrade) {
        this.doctorGrade = doctorGrade == null ? null : doctorGrade.trim();
    }

    public String getDoctorAdd() {
        return doctorAdd;
    }

    public void setDoctorAdd(String doctorAdd) {
        this.doctorAdd = doctorAdd == null ? null : doctorAdd.trim();
    }

    public String getDoctorInfoadd() {
        return doctorInfoadd;
    }

    public void setDoctorInfoadd(String doctorInfoadd) {
        this.doctorInfoadd = doctorInfoadd == null ? null : doctorInfoadd.trim();
    }

    public String getDoctorBirthday() {
        return doctorBirthday;
    }

    public void setDoctorBirthday(String doctorBirthday) {
        this.doctorBirthday = doctorBirthday == null ? null : doctorBirthday.trim();
    }

    public String getDoctorSex() {
        return doctorSex;
    }

    public void setDoctorSex(String doctorSex) {
        this.doctorSex = doctorSex == null ? null : doctorSex.trim();
    }

    public String getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(String doctorInfo) {
        this.doctorInfo = doctorInfo == null ? null : doctorInfo.trim();
    }

    public String getDoctorGood() {
        return doctorGood;
    }

    public void setDoctorGood(String doctorGood) {
        this.doctorGood = doctorGood == null ? null : doctorGood.trim();
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId == null ? null : hospitalId.trim();
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser == null ? null : deleteUser.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}