package com.wyu.takeleave.student;

import com.wyu.takeleave.BaseActivityPresenter;
import com.wyu.takeleave.ValueCallBack;
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
        model.getUser(userInfo, new ValueCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                view.setView(userInfo);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void handleGetTakeLeave() {
        model.getTakeLeaveForm(new ValueCallBack<ArrayList<FormBrief>>() {
            @Override
            public void onSuccess(ArrayList<FormBrief> formBriefs) {
                view.setListView(formBriefs);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }


    @Override
    public Boolean logout() {
        //处理注销事件
        return true;
    }
}
