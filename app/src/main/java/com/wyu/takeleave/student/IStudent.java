package com.wyu.takeleave.student;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

public interface IStudent {
    interface View{
        void initRecycleView(ArrayList<FormBrief> formBriefs);
        void setView(UserInfo userInfo);
        void setListView(ArrayList<FormBrief> formBriefs);
        void refresh();
    }

    interface Presenter{
        Boolean logout();
        void handleGetUser(UserInfo userInfo);
        void handleGetTakeLeave();
        UserInfo handleGetUserInfo();  //P层获取用户信息
    }

    interface Model{
        void getUser(UserInfo userInfo, ValueCallBack<UserInfo> gettingDataListener);
        void getTakeLeaveForm(ValueCallBack<ArrayList<FormBrief>> gettingTakeLeaveListener);
        UserInfo getUserInfo();  //M层获取用户信息
    }
}
