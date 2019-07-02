package com.wyu.takeleave.util;

import java.io.Serializable;
import java.util.Date;

//表单实体
public class TakeLeaveForm implements Serializable {
    private Integer form_id;

    private String user_id;

    private String class_id;

    private String college;

    private String major;

    private String guardian_tel;

    private String guardian_name;

    private String take_days;

    private String begin_days;

    private String dead_days;

    private String reason;

    private Byte instructor_permit;

    private String student_tel;

    private String username;

    private Date applyTime;

    public Integer getForm_id() {
        return form_id;
    }

    public void setForm_id(Integer form_id) {
        this.form_id = form_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGuardian_tel() {
        return guardian_tel;
    }

    public void setGuardian_tel(String guardian_tel) {
        this.guardian_tel = guardian_tel;
    }

    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public String getTake_days() {
        return take_days;
    }

    public void setTake_days(String take_days) {
        this.take_days = take_days;
    }

    public String getBegin_days() {
        return begin_days;
    }

    public void setBegin_days(String begin_days) {
        this.begin_days = begin_days;
    }

    public String getDead_days() {
        return dead_days;
    }

    public void setDead_days(String dead_days) {
        this.dead_days = dead_days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Byte getInstructor_permit() {
        return instructor_permit;
    }

    public void setInstructor_permit(Byte instructor_permit) {
        this.instructor_permit = instructor_permit;
    }

    public String getStudent_tel() {
        return student_tel;
    }

    public void setStudent_tel(String student_tel) {
        this.student_tel = student_tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
}
