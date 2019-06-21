package com.wyu.takeleave.teacher;

import com.wyu.takeleave.BaseActivityPresenter;

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
}
