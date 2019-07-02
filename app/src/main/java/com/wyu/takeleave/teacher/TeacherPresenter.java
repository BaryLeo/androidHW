package com.wyu.takeleave.teacher;

import com.wyu.takeleave.BaseActivityPresenter;
import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.form.Form;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.UserInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TeacherPresenter extends BaseActivityPresenter<Teacher> implements ITeacher.Presenter{
    private ITeacher.View view;

    private ITeacher.Model model;

    public TeacherPresenter(ITeacher.View view){
        this.view=view;
        this.model = new TeacherModel();
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
    public void handleGetTakeLeaveForm(int formId) {
        //调用这个view.setTakeLeaveForm();
        view.setTakeLeaveForm(model.getTakeLeaveForm(formId));
        //当获取成功时
        view.toFormView();
    }

    @Override
    public void handleGetTakeLeaveForms() {
        model.getTakeLeaveForms(new ValueCallBack<ArrayList<FormBrief>>() {
            @Override
            public void onSuccess(ArrayList<FormBrief> formBriefs) {
                //TODO:等家杰改好view层，这里setView即可
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
        return false;
    }
}
