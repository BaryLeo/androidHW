package com.wyu.takeleave.student;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.MyOkHttpUtils;
import com.wyu.takeleave.util.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;

interface OnGettingDataListener{
    void gettingSuccess(UserInfo userInfo);
    void gettingFailed();
}

//JSON解析类，这个用于解析GetUser接口
class UserData {

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

public class StudentModel implements IStudent.Model{

    private final String GETUSER = "http://appcat.site/api/getUser";
    private final String GETFORM = "http://appcat.site/api/student/getTakeLeaveForm";
    private UserInfo localUserInfo = new UserInfo();

    @Override
    public void getUser(UserInfo userInfo, ValueCallBack<UserInfo> callBack) {
        try{
            OkHttpUtils
                    .post()
                    .url(GETUSER)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            callBack.onFail(e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if(!response.equals("")){
                                UserData data = (UserData) MyOkHttpUtils.parseJSONWithGSON(response, UserData.class);
                                userInfo.setName(data.getContent().getUsername());
                                localUserInfo.setName(userInfo.getName());
                                localUserInfo.setUserType(userInfo.getUserType());
                                localUserInfo.setId(userInfo.getId());
                                callBack.onSuccess(localUserInfo);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getTakeLeaveForm(ValueCallBack<ArrayList<FormBrief>> gettingTakeLeaveListener) {
        try{
            OkHttpUtils
                    .post()
                    .url(GETFORM)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if(!response.equals("")){
                                StudentLeaveInfo info = (StudentLeaveInfo) MyOkHttpUtils.parseJSONWithGSON(response, StudentLeaveInfo.class);
                                ArrayList<FormBrief> formBriefs = new ArrayList<FormBrief>();
                                for(int i = 0; i < info.getContent().size(); i++){
                                    FormBrief data = new FormBrief();
                                    //data.setAuditor();
                                    data.setDuration(info.getContent().get(i).getTakeDays());
                                    //data.setReply();
                                    //data.setStatus();
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    try {
                                        Date date = simpleDateFormat.parse(info.getContent().get(i).getApplyTime());
                                        data.setTime(date);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    formBriefs.add(data);
                                }
                                gettingTakeLeaveListener.onSuccess(formBriefs);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
