package com.wyu.takeleave.teacher;

public class ATeacherData {

    public class Content {
        private String name;

        private String gender;

        private String department;

        private String duty;

        private String tel;

        private String remarks;

        private int isFirstLogin;

        private String teacherId;

        private String positionalTitle;

        private String officeTel;

        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setGender(String gender){
            this.gender = gender;
        }
        public String getGender(){
            return this.gender;
        }
        public void setDepartment(String department){
            this.department = department;
        }
        public String getDepartment(){
            return this.department;
        }
        public void setDuty(String duty){
            this.duty = duty;
        }
        public String getDuty(){
            return this.duty;
        }
        public void setTel(String tel){
            this.tel = tel;
        }
        public String getTel(){
            return this.tel;
        }
        public void setRemarks(String remarks){
            this.remarks = remarks;
        }
        public String getRemarks(){
            return this.remarks;
        }
        public void setIsFirstLogin(int isFirstLogin){
            this.isFirstLogin = isFirstLogin;
        }
        public int getIsFirstLogin(){
            return this.isFirstLogin;
        }
        public void setTeacherId(String teacherId){
            this.teacherId = teacherId;
        }
        public String getTeacherId(){
            return this.teacherId;
        }
        public void setPositionalTitle(String positionalTitle){
            this.positionalTitle = positionalTitle;
        }
        public String getPositionalTitle(){
            return this.positionalTitle;
        }
        public void setOfficeTel(String officeTel){
            this.officeTel = officeTel;
        }
        public String getOfficeTel(){
            return this.officeTel;
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