package com.wyu.takeleave.teacher;

import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

public interface ITeacher {
    interface View{
    }

    interface Presenter{
        ArrayList<FormBrief> setViewData();

        Boolean logout();
    }

    interface Model{

    }
}
