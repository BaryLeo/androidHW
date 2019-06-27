package com.wyu.takeleave.teacher;

import com.wyu.takeleave.BaseActivityPresenter;
import com.wyu.takeleave.form.Form;
import com.wyu.takeleave.util.FormBrief;

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
        ArrayList<FormBrief> formBriefs = new ArrayList<FormBrief>();
        for (int i =0;i<5;i++){
            FormBrief formBrief = new FormBrief();
            formBrief.setTime(new Date());
            formBrief.setStatus(i);
            formBriefs.add(formBrief);
        }
        return formBriefs;
    }
}
