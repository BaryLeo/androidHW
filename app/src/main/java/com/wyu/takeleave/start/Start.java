package com.wyu.takeleave.start;

import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.IntentActivity;
import com.wyu.takeleave.R;
import com.wyu.takeleave.login.Login;

import java.util.Timer;
import java.util.TimerTask;

public class Start extends BaseActivity<StartPresenter> implements IStart.View{


    @Override
    protected StartPresenter initPresent() {
        //连接presenter
        return new StartPresenter(this);
    }

    @Override
    protected int getLayout() {
        //绑定layout
        return R.layout.start;
    }

    @Override
    protected void initView() {
        //视图初始化
    }

    @Override
    protected void onPrepare() {
        //业务逻辑层
        //表示延时2秒进行跳转
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                intentToLogin();

            }
        },2000);
    }

    @Override
    public void intentToLogin() {
        //不携带数据跳转
        IntentActivity.intentWithoutData(this, Login.class);
        //销毁本activity，并回收内存
        IntentActivity.finishActivity(this);
    }
}
