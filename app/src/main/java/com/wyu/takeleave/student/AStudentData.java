package com.wyu.takeleave.student;

//JSON解析类，这个用于解析GetUser接口
public class AStudentData {

    public class Content {
        private String gender;

        private String volk;

        private String grade;

        private String major;

        private String college;

        private String mail;

        private String tel;

        private String duty;

        private String username;

        private int isFirstLogin;

        private String classId;

        private String lengthOfSchooling;

        private String politicalStatus;

        private String userId;

        private String enrolTime;

        private String citizenId;

        private String studentStatus;

        public void setGender(String gender){
            this.gender = gender;
        }
        public String getGender(){
            return this.gender;
        }
        public void setVolk(String volk){
            this.volk = volk;
        }
        public String getVolk(){
            return this.volk;
        }
        public void setGrade(String grade){
            this.grade = grade;
        }
        public String getGrade(){
            return this.grade;
        }
        public void setMajor(String major){
            this.major = major;
        }
        public String getMajor(){
            return this.major;
        }
        public void setCollege(String college){
            this.college = college;
        }
        public String getCollege(){
            return this.college;
        }
        public void setMail(String mail){
            this.mail = mail;
        }
        public String getMail(){
            return this.mail;
        }
        public void setTel(String tel){
            this.tel = tel;
        }
        public String getTel(){
            return this.tel;
        }
        public void setDuty(String duty){
            this.duty = duty;
        }
        public String getDuty(){
            return this.duty;
        }
        public void setUsername(String username){
            this.username = username;
        }
        public String getUsername(){
            return this.username;
        }
        public void setIsFirstLogin(int isFirstLogin){
            this.isFirstLogin = isFirstLogin;
        }
        public int getIsFirstLogin(){
            return this.isFirstLogin;
        }
        public void setClassId(String classId){
            this.classId = classId;
        }
        public String getClassId(){
            return this.classId;
        }
        public void setLengthOfSchooling(String lengthOfSchooling){
            this.lengthOfSchooling = lengthOfSchooling;
        }
        public String getLengthOfSchooling(){
            return this.lengthOfSchooling;
        }
        public void setPoliticalStatus(String politicalStatus){
            this.politicalStatus = politicalStatus;
        }
        public String getPoliticalStatus(){
            return this.politicalStatus;
        }
        public void setUserId(String userId){
            this.userId = userId;
        }
        public String getUserId(){
            return this.userId;
        }
        public void setEnrolTime(String enrolTime){
            this.enrolTime = enrolTime;
        }
        public String getEnrolTime(){
            return this.enrolTime;
        }
        public void setCitizenId(String citizenId){
            this.citizenId = citizenId;
        }
        public String getCitizenId(){
            return this.citizenId;
        }
        public void setStudentStatus(String studentStatus){
            this.studentStatus = studentStatus;
        }
        public String getStudentStatus(){
            return this.studentStatus;
        }

    }

    private Content content;

    public void setContent(Content content){
        this.content = content;
    }
    public Content getContent(){
        return this.content;
    }

}
