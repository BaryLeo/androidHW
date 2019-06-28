package com.wyu.takeleave.teacher;

import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;

import java.util.ArrayList;

public interface ITeacher {
    interface View{
    }

    interface Presenter{
        ArrayList<FormBrief> setViewData();
    }

    interface Model{

    }
}
