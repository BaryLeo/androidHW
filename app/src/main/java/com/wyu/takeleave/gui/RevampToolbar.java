package com.wyu.takeleave.gui;

import android.widget.TextView;

import com.wyu.takeleave.R;

public class RevampToolbar {
    /**
     * 设置toolbar上的返回按钮
     * @param back
     */
    public static void setBack(TextView back){
        //设置返回图标
        back.setBackground(TakeLeaveApp.getContext().getResources().getDrawable(R.drawable.back_black));
    }

    /**
     * 设置toolbar显示的文字
     * @param text
     * @param title
     */
    public static void setText(TextView text,String title){
        //设置toolbar正中的标题
        text.setText(title);
    }
}
