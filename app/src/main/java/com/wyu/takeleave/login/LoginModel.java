package com.wyu.takeleave.login;

import com.wyu.takeleave.util.UserInfo;

interface OnLoginListener{
    void loginSuccess(UserInfo userInfo);
    void loginFailed();
}

public class LoginModel implements ILogin.Model{
    @Override
    public void login(String id, String password, OnLoginListener loginListener) {
        if("admin".equals(id) && "123".equals(password)){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(id);
            userInfo.setName("管理员");
            userInfo.setUserType("老师");
            loginListener.loginSuccess(userInfo);
        }else{
            loginListener.loginFailed();
        }
    }
}
