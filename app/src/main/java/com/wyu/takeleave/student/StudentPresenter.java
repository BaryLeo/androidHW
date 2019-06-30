package com.wyu.takeleave.student;

import com.wyu.takeleave.BaseActivityPresenter;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;
import java.util.Date;

public class StudentPresenter extends BaseActivityPresenter<Student> implements IStudent.Presenter{
    private IStudent.View view;

    private IStudent.Model model;

    public StudentPresenter(IStudent.View view){
        this.view=view;
        this.model = new StudentModel();
    }


    @Override
    protected void deleteView() {
        //解除引用，GC回收
        view = null;
        model = null;
    }

    @Override
    public void handleGetUser(UserInfo userInfo) {
        model.getUser(userInfo, new OnGettingDataListener() {
            @Override
            public void gettingSuccess(UserInfo userInfo) {
                view.setView(userInfo);
            }

            @Override
            public void gettingFailed() {

            }
        });
    }

    @Override
    public ArrayList<FormBrief> setViewData() {
        ArrayList<FormBrief> formBriefs = new ArrayList<FormBrief>();
        for (int i =0;i<5;i++){
            FormBrief formBrief = new FormBrief();
            formBrief.setTime(new Date());
            formBrief.setStatus(i);
            formBriefs.add(formBrief);
        }
        return formBriefs;
    }

    @Override
    public Boolean logout() {
        //处理注销事件
        return true;
    }
}
