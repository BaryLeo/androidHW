package com.wyu.takeleave.student;

import java.util.List;

public class AStudentLeaveInfo {

    public class Content {
        private String college;

        private String major;

        private String reason;

        private String instructor_permit;

        private String student_tel;

        private String username;

        private String applyTime;

        private String userId;

        private String classId;

        private String guardianName;

        private String guardianTel;

        private String takeDays;

        private String beginDays;

        private String deadDays;

        private int formId;

        public void setCollege(String college){
            this.college = college;
        }
        public String getCollege(){
            return this.college;
        }
        public void setMajor(String major){
            this.major = major;
        }
        public String getMajor(){
            return this.major;
        }
        public void setReason(String reason){
            this.reason = reason;
        }
        public String getReason(){
            return this.reason;
        }
        public void setInstructor_permit(String instructor_permit){
            this.instructor_permit = instructor_permit;
        }
        public String getInstructor_permit(){
            return this.instructor_permit;
        }
        public void setStudent_tel(String student_tel){
            this.student_tel = student_tel;
        }
        public String getStudent_tel(){
            return this.student_tel;
        }
        public void setUsername(String username){
            this.username = username;
        }
        public String getUsername(){
            return this.username;
        }
        public void setApplyTime(String applyTime){
            this.applyTime = applyTime;
        }
        public String getApplyTime(){
            return this.applyTime;
        }
        public void setUserId(String userId){
            this.userId = userId;
        }
        public String getUserId(){
            return this.userId;
        }
        public void setClassId(String classId){
            this.classId = classId;
        }
        public String getClassId(){
            return this.classId;
        }
        public void setGuardianName(String guardianName){
            this.guardianName = guardianName;
        }
        public String getGuardianName(){
            return this.guardianName;
        }
        public void setGuardianTel(String guardianTel){
            this.guardianTel = guardianTel;
        }
        public String getGuardianTel(){
            return this.guardianTel;
        }
        public void setTakeDays(String takeDays){
            this.takeDays = takeDays;
        }
        public String getTakeDays(){
            return this.takeDays;
        }
        public void setBeginDays(String beginDays){
            this.beginDays = beginDays;
        }
        public String getBeginDays(){
            return this.beginDays;
        }
        public void setDeadDays(String deadDays){
            this.deadDays = deadDays;
        }
        public String getDeadDays(){
            return this.deadDays;
        }
        public void setFormId(int formId){
            this.formId = formId;
        }
        public int getFormId(){
            return this.formId;
        }

    }

    private List<Content> content ;

    public void setContent(List<Content> content){
        this.content = content;
    }
    public List<Content> getContent(){
        return this.content;
    }

}
