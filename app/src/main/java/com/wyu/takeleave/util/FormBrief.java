package com.wyu.takeleave.util;

import java.io.Serializable;
import java.util.Date;

public class FormBrief implements Serializable {
    //申请时间
    private String time;
    //0 拒绝，1通过，2班导审批，3辅导员审批，4院长审批
    private int status;
    private String auditor;
    //请假时长
    private String duration;
    private String reply;
    private int formID;

    public FormBrief(){
        status = 2;
        auditor ="暂无审核者信息";
        duration = "获取失败";
        reply = "无";
    }

    public int getFormID() {
        return formID;
    }

    public void setFormID(int formID) {
        this.formID = formID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
