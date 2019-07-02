package com.wyu.takeleave.teacher;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.IntentActivity;
import com.wyu.takeleave.R;
import com.wyu.takeleave.form.Form;
import com.wyu.takeleave.login.Login;
import com.wyu.takeleave.student.Student;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.FormBrielAdapter;
import com.wyu.takeleave.util.OnItemClickListener;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.util.ArrayList;

import static com.wyu.takeleave.util.TakeLeaveApp.getContext;

public class Teacher extends BaseActivity<TeacherPresenter> implements ITeacher.View{
    //侧边栏组件
    private NavigationView navigationView;
    private TextView id;
    private TextView name;
    private RecyclerView mRecyclerView;
    private FormBrielAdapter adapter;
    private RefreshLayout refreshLayout;
    private TakeLeaveForm takeLeaveForm;
    private ArrayList<FormBrief> formBriefs;
    private Intent intent;
    private FormBrief formBrief;
    private Toolbar toolbar;

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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        presenter.handleGetUser(((UserInfo) getIntent().getSerializableExtra("userInfo")));
        formBriefs = new ArrayList<FormBrief>();

        /**
         * 通过侧边栏组件，才能操作侧边栏里面的组件
         */
        id = navigationView.getHeaderView(0).findViewById(R.id.userId);
        name = navigationView.getHeaderView(0).findViewById(R.id.userName);
        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recylist);
        /**
         * 设置线性管理器
         */
        presenter.handleGetTakeLeaveForms();  //开始之前，先获取到信息并set好

        /**
         * 响应侧边栏事件
         */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.allApply:{
                        /**
                         * 更新adapter内部数据
                         */
                        Teacher.this.adapter=new FormBrielAdapter(formBriefs);
                        /**
                         * 刷新recycleView
                         */
                        Teacher.this.adapter.notifyDataSetChanged();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case R.id.noCheckApply:{
                        ArrayList<FormBrief> formBriefs= new ArrayList<>();
                        for (FormBrief formBrief:formBriefs){
                            if (formBrief.getStatus()==2||formBrief.getStatus()==3||formBrief.getStatus()==4){
                                formBriefs.add(formBrief);
                            }
                        }
                        //更新adapter内部数据
                        Teacher.this.adapter=new FormBrielAdapter(formBriefs);
                        //刷新recycleView
                        Teacher.this.adapter.notifyDataSetChanged();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case R.id.checkedApply:{
                        ArrayList<FormBrief> formBriefs= new ArrayList<>();
                        for (FormBrief formBrief:formBriefs){
                            if (formBrief.getStatus()==0||formBrief.getStatus()==1){
                                formBriefs.add(formBrief);
                            }
                        }
                        //更新adapter内部数据
                        Teacher.this.adapter=new FormBrielAdapter(formBriefs);
                        //刷新recycleView
                        Teacher.this.adapter.notifyDataSetChanged();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case R.id.nav_share:{
                        /**
                         * 处理注销事件
                         */
                        if (presenter.logout()){
                            Toast.makeText(Teacher.this, "注销成功", Toast.LENGTH_SHORT).show();
                            //不携带数据跳转
                            IntentActivity.intentWithoutData(Teacher.this, Login.class);
                            //销毁本activity，并回收内存
                            IntentActivity.finishActivity(Teacher.this);
                        }else {
                            Toast.makeText(Teacher.this, "注销失败", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onPrepare() {
        /**
         * 页面刷新响应
         */
        refresh();
    }

    @Override
    public void setView(UserInfo userInfo) {
        id.setText(userInfo.getId());
        name.setText(userInfo.getName());
    }

    @Override
    public void setTakeLeaveForm(TakeLeaveForm takeLeaveForm) {
        this.takeLeaveForm = takeLeaveForm;
    }

    @Override
    public void toFormView() {
        intent.putExtra("takeLeaveForm",takeLeaveForm);
        startActivity(intent);
        //销毁本activity，并回收内存
        IntentActivity.finishActivity(this);
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            ArrayList<FormBrief> formBriefArrayList;
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //进行网络请求
                //TODO:这里还要做处理，网络请求不是简单的set即可
//                formBriefArrayList = presenter.setViewData();
//                if (formBriefArrayList==null){
//                    refreshlayout.finishRefresh(1000,false);//传入false表示刷新失败
//                    Toast.makeText(Teacher.this,"刷新失败",Toast.LENGTH_SHORT).show();
//                }else {
//                    //更新adapter内部数据
//                    Teacher.this.adapter=new FormBrielAdapter(formBriefArrayList);
//                    //刷新recycleView
//                    Teacher.this.adapter.notifyDataSetChanged();
//                    Toast.makeText(Teacher.this,"刷新成功",Toast.LENGTH_SHORT).show();
//                    refreshlayout.finishRefresh(1000/*,false*/);
//                }
//                refreshlayout.finishRefresh(1000/*,false*/);
            }
        });
    }

    @Override
    public void setFormBriefs(ArrayList<FormBrief> forms) {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        this.formBriefs = forms;
        adapter = new FormBrielAdapter(forms);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(Teacher.this, Form.class);
                intent.putExtra("userInfo", presenter.handleGetUserInfo());
                formBrief=formBriefs.get(position);
                formBrief.setIsPut(0);
                intent.putExtra("isPut",formBrief);
                presenter.handleGetTakeLeaveForm(formBriefs.get(position).getFormID());
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
