package com.wyu.takeleave.student;

import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

public interface IStudent {
    interface View{
        void initRecycleView(ArrayList<FormBrief> formBriefs);
        void setView(UserInfo userInfo);
    }

    interface Presenter{
        ArrayList<FormBrief> setViewData();
        Boolean logout();
        void handleGetUser(UserInfo userInfo);
    }

    interface Model{
        void getUser(UserInfo userInfo, OnGettingDataListener gettingDataListener);
    }
}
