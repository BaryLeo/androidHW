package com.wyu.takeleave.student;

import android.support.design.widget.NavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.R;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.FormBrielAdapter;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

import static com.wyu.takeleave.util.TakeLeaveApp.getContext;

public class Student extends BaseActivity<StudentPresenter> implements IStudent.View{
    //侧边栏组件
    private NavigationView navigationView;
    private TextView id;
    private TextView name;
    private RecyclerView mRecyclerView;
    private FormBrielAdapter adapter;

    @Override
    protected StudentPresenter initPresent() {
        return new StudentPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main_student;
    }

    @Override
    protected void initView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //通过侧边栏组件，才能操作侧边栏里面的组件
        id = navigationView.getHeaderView(0).findViewById(R.id.userId);
        name = navigationView.getHeaderView(0).findViewById(R.id.userName);
        id.setText(getIntent().getStringExtra("userId"));
        name.setText(getIntent().getStringExtra("userName"));

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recylist);
        //设置线性管理器
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new FormBrielAdapter(presenter.setViewData());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPrepare() {

    }


    @Override
    public void initRecycleView(ArrayList<FormBrief> formBriefs) {

    }
}
