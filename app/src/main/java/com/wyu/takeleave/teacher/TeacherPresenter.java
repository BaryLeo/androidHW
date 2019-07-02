package com.wyu.takeleave.teacher;

import com.wyu.takeleave.BaseActivityPresenter;
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
    public ArrayList<FormBrief> setViewData() {
        //只需要给我一个arraylist就好了
        //还有这个对象的特殊说明，特别是这个status,看一下这个实体类的注释
        //还有这个对象的特殊说明，特别是这个status,看一下这个实体类的注释
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ArrayList<FormBrief> formBriefs = new ArrayList<FormBrief>();
        for (int i =0;i<5;i++){
            FormBrief formBrief = new FormBrief();
            formBrief.setTime(format.format(new Date()));
            formBrief.setStatus(i);
            formBriefs.add(formBrief);
        }
        return formBriefs;
    }

    @Override
    public void handleGetUser(UserInfo userInfo) {

    }

    @Override
    public void getTakeLeaveForm(int formID) {
        //调用这个view.setTakeLeaveForm();
    }

    @Override
    public Boolean logout() {
        //处理注销事件
        return false;
    }
}
