package com.wyu.takeleave.student;

import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;

import java.util.ArrayList;

public interface IStudent {
    interface View{
        void initRecycleView(ArrayList<FormBrief> formBriefs);
    }

    interface Presenter{
        ArrayList<FormBrief> setViewData();
    }

    interface Model{

    }
}
