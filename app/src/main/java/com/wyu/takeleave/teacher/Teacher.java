package com.wyu.takeleave.teacher;

import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.R;

public class Teacher extends BaseActivity<TeacherPresenter> implements ITeacher.View{
    @Override
    protected TeacherPresenter initPresent() {
        return new TeacherPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main_teacher;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onPrepare() {

    }
}
