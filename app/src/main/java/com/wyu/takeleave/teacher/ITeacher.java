package com.wyu.takeleave.teacher;

import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

public interface ITeacher {
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
        void handleGetUser(UserInfo userInfo);
        void getTakeLeaveForm(int formID);
        Boolean logout();
    }

    interface Model{

    }
}
