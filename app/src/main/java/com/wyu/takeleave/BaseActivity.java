package com.wyu.takeleave;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.wyu.takeleave.util.RevampStatusBar;

public abstract class BaseActivity<T extends BaseActivityPresenter> extends AppCompatActivity {

    protected T presenter;       //presenter层的实例对象，用于view与presenter交互

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        if(Build.VERSION.SDK_INT>=21){
            Window window=getWindow();
            View view=window.getDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            );
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        RevampStatusBar.FlymeSetStatusBarLightMode(getWindow(),true);
        RevampStatusBar.MIUISetStatusBarLightMode(getWindow(),true);
        setContentView(getLayout());
        presenter=initPresent();
        initView();
        onPrepare();

    }

    protected abstract T initPresent();      //为presenter添加view弱引用
    protected abstract int getLayout();       //为活动添加页面
    protected abstract void initView();      //UI组件初始化
    protected abstract void onPrepare();     //对组件事件进行处理

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.deleteView();
        presenter=null;
    }

}