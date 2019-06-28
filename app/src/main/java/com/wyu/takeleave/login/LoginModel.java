package com.wyu.takeleave.login;

import android.util.Log;

import com.wyu.takeleave.util.MyOkHttpUtils;
import com.wyu.takeleave.util.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

interface OnLoginListener{
    void loginSuccess(UserInfo userInfo);
    void loginFailed();
}

//JSON解析类，这个用于解析Login接口
class LoginData {
    class Authorities {
        private String authority;

        public void setAuthority(String authority){
            this.authority = authority;
        }
        public String getAuthority(){
            return this.authority;
        }

    }

    private List<Authorities> authorities;
    private String name;

    public void setAuthorities(List<Authorities> authorities){
        this.authorities = authorities;
    }
    public List<Authorities> getAuthorities(){
        return this.authorities;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

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

public class LoginModel implements ILogin.Model {

    private final String LOGIN = "http://appcat.site/api/login";
    private final String GETUSER = "http://appcat.site/api/getUser";


    @Override
    public void login(String id, String password, OnLoginListener loginListener) {
        //Params的情况
        try {
            OkHttpUtils
                    .post()
                    .url(LOGIN)
                    .addParams("userID", id)
                    .addParams("password", password)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            loginListener.loginFailed();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if(!response.equals("")){
                                LoginData data = (LoginData) MyOkHttpUtils.parseJSONWithGSON(response, LoginData.class);
                                UserInfo userInfo = new UserInfo();
                                userInfo.setId(data.getName());
                                userInfo.setUserType(data.getAuthorities().get(0).getAuthority());
//                                try{
//                                    OkHttpUtils
//                                            .post()
//                                            .url(GETUSER)
//                                            .build()
//                                            .execute(new StringCallback() {
//                                                @Override
//                                                public void onError(Call call, Exception e, int id) {
//                                                    e.printStackTrace();
//                                                }
//
//                                                @Override
//                                                public void onResponse(String response, int id) {
//                                                    UserData data = (UserData) MyOkHttpUtils.parseJSONWithGSON(response, UserData.class);
//                                                    userInfo.setName(data.getContent().getUsername());
//                                                }
//                                            });
//                                }catch (Exception e){
//                                    e.printStackTrace();
//                                }
                                loginListener.loginSuccess(userInfo);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getUser(UserInfo userInfo) {

    }
}
