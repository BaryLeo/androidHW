package com.wyu.takeleave.student;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

public interface IStudent {
    interface View{
        void setView(UserInfo userInfo);
        void setFormBriefs(ArrayList<FormBrief> formBriefs);
        void refresh();
        void setTakeLeaveForm(TakeLeaveForm takeLeaveForm);
        //获取表单成功时，跳转
        void toFormView();
        //获取失败
        void fail(String msg);
    }

    interface Presenter{
        Boolean logout();
        void handleGetUser(UserInfo userInfo);
        void handleGetTakeLeave();  //获取请假审批列表
        UserInfo handleGetUserInfo();  //P层获取用户信息
        void getTakeLeaveForm(int formID);
    }

    interface Model{
        void getUser(UserInfo userInfo, ValueCallBack<UserInfo> gettingDataListener);
        TakeLeaveForm getTakeLeaveForm(int formId);
        void getTakeLeaveForms(ValueCallBack<ArrayList<FormBrief>> gettingTakeLeaveListener);
        UserInfo getUserInfo();  //M层获取用户信息
    }
}
