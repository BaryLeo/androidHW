package com.wyu.takeleave.login;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.UserInfo;

public interface ILogin {
    interface View{
        //若成功，则返回用户数据,数据中包含用户信息,name,id,type
        void toMain(UserInfo userInfo);
        //返回错误信息，并告知用户
        void loginFailure(String msg);
    }

    interface Presenter{
        //事物处理逻辑
        void HandleLogin(String id,String password);
    }

    interface Model{
        void login(String id, String password, OnLoginListener loginListener);
    }
}
