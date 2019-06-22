package com.wyu.takeleave.teacher;

import android.support.design.widget.NavigationView;
import android.widget.TextView;

import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.R;
import com.wyu.takeleave.util.UserInfo;

public class Teacher extends BaseActivity<TeacherPresenter> implements ITeacher.View{
    //侧边栏组件
    private NavigationView navigationView;
    private TextView id;
    private TextView name;

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
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //通过侧边栏组件，才能操作侧边栏里面的组件
        id = navigationView.getHeaderView(0).findViewById(R.id.userId);
        name = navigationView.getHeaderView(0).findViewById(R.id.userName);
        id.setText(getIntent().getStringExtra("userId"));
        name.setText(getIntent().getStringExtra("userName"));
    }

    @Override
    protected void onPrepare() {

    }
}