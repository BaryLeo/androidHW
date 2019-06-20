package com.wyu.takeleave.gui;

import android.app.Application;
import android.content.Context;

/**
 * 通过静态方法getContext()获取全局Context
 */
public class TakeLeaveApp extends Application {
    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
