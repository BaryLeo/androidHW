package com.wyu.takeleave.student;

import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

public interface IStudent {
    interface View{
        void setView(UserInfo userInfo);
        void refresh();
        void setTakeLeaveForm(TakeLeaveForm takeLeaveForm);
        //获取表单成功时，跳转
        void toFormView();
        //获取失败
        void fail(String msg);
    }

    interface Presenter{
        ArrayList<FormBrief> setViewData();
        Boolean logout();
        void handleGetUser(UserInfo userInfo);
        void getTakeLeaveForm(int formID);
    }

    interface Model{
        void getUser(UserInfo userInfo, OnGettingDataListener gettingDataListener);
    }
}
