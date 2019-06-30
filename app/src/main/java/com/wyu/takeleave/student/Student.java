package com.wyu.takeleave.student;

import android.support.design.widget.NavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
    private RefreshLayout refreshLayout;

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
        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recylist);
        //设置线性管理器
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new FormBrielAdapter(presenter.setViewData());
        mRecyclerView.setAdapter(adapter);


        presenter.handleGetUser(((UserInfo) getIntent().getSerializableExtra("userInfo")));
    }

    @Override
    protected void onPrepare() {
        //页面刷新响应
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            ArrayList<FormBrief> formBriefArrayList;
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //进行网络请求
                formBriefArrayList = presenter.setViewData();
                if (formBriefArrayList==null){
                    refreshlayout.finishRefresh(2000,false);//传入false表示刷新失败
                    Toast.makeText(Student.this,"刷新失败",Toast.LENGTH_SHORT).show();
                }else {
                    //更新adapter内部数据
                    Student.this.adapter=new FormBrielAdapter(formBriefArrayList);
                    //刷新recycleView
                    Student.this.adapter.notifyDataSetChanged();
                    Toast.makeText(Student.this,"刷新成功",Toast.LENGTH_SHORT).show();
                    refreshlayout.finishRefresh(1000/*,false*/);
                }
                refreshlayout.finishRefresh(1000/*,false*/);
            }
        });


        //响应侧边栏按钮

    }


    @Override
    public void initRecycleView(ArrayList<FormBrief> formBriefs) {

    }

    @Override
    public void setView(UserInfo userInfo) {
        id.setText(userInfo.getId());
        name.setText(userInfo.getName());
    }


}
