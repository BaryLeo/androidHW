package com.wyu.takeleave.login;

import android.util.Log;

import com.wyu.takeleave.util.MyOkHttpUtils;
import com.wyu.takeleave.util.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

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
public class LoginModel implements ILogin.Model {

    private final String LOGIN = "http://appcat.site/api/login";


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
                                loginListener.loginSuccess(userInfo);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
