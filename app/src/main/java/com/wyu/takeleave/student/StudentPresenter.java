package com.wyu.takeleave.student;

import com.wyu.takeleave.BaseActivityPresenter;
import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.text.SimpleDateFormat;
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
    public void getTakeLeaveForm(int formID) {
        //调用view.setTakeLeaveForm();
        TakeLeaveForm takeLeaveForm = new TakeLeaveForm();
        takeLeaveForm.setGuardian_tel("1");
        takeLeaveForm.setGuardian_name("1");
        takeLeaveForm.setStudent_tel("1");
        takeLeaveForm.setMajor("1");
        takeLeaveForm.setClass_id("1");
        takeLeaveForm.setCollege("1");
        takeLeaveForm.setUser_id("1");
        takeLeaveForm.setUsername("1");
        takeLeaveForm.setTake_days("1");
        takeLeaveForm.setDead_days("1");
        takeLeaveForm.setBegin_days("1");
        takeLeaveForm.setReason("1");
        view.setTakeLeaveForm(takeLeaveForm);
        //当获取成功时
        view.toFormView();
    }

    @Override
    public void handleGetTakeLeave() {
        model.getTakeLeaveForms(new ValueCallBack<ArrayList<FormBrief>>() {
            @Override
            public void onSuccess(ArrayList<FormBrief> formBriefs) {
                view.setFormBriefs(formBriefs);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public UserInfo handleGetUserInfo() {
        return model.getUserInfo();
    }


    @Override
    public Boolean logout() {
        //处理注销事件
        return true;
    }
}
