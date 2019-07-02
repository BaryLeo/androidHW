package com.wyu.takeleave.teacher;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

public interface ITeacher {
    interface View{
        void setView(UserInfo userInfo);
        void refresh();
        void setTakeLeaveForm(TakeLeaveForm takeLeaveForm);
        void setFormBriefs(ArrayList<FormBrief> formBriefs);
        //获取表单成功时，跳转
        void toFormView();
        //获取失败
        void fail(String msg);
    }

    interface Presenter{
        void handleGetUser(UserInfo userInfo);
        void handleGetTakeLeaveForms();  //获取请假审批列表
        void handleGetTakeLeaveForm(int formId);  //获取单个请假详情
        UserInfo handleGetUserInfo();  //P层获取用户信息
        Boolean logout();
    }

    interface Model{
        void getUser(UserInfo userInfo, ValueCallBack<UserInfo> gettingDataListener);
        TakeLeaveForm getTakeLeaveForm(int formId);
        void getTakeLeaveForms(ValueCallBack<ArrayList<FormBrief>> gettingTakeLeaveListener);  //获取请假审批列表
        UserInfo getUserInfo();  //M层获取用户信息
    }
}
