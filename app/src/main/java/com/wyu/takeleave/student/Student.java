package com.wyu.takeleave.student;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.R;
import com.wyu.takeleave.gui.RevampStatusBar;
import com.wyu.takeleave.gui.RevampToolbar;

public class Student extends BaseActivity<StudentPresenter> implements IStudent.View{

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
    }

    @Override
    protected void onPrepare() {

    }



}
