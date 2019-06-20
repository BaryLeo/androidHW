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
        //业务逻辑预处理


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
    protected void onPrepare() {

    }

    @Override
    public void intentToLogin() {
        IntentActivity.intentWithoutData(this, Login.class);
        IntentActivity.finishActivity(this);
    }
}
