package com.wyu.takeleave;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends BaseFragmentPresenter> extends Fragment {
    protected T presenter;       //presenter层的实例对象，用于view与presenter交互
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view=inflater.inflate(getLayout(),group,false);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //可见时进行内容加载或逻辑操作等
        } else {
            //不可见
        }
    }
    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        presenter=initPresent();
        initView();
        onPrepare();
    }
    protected abstract T initPresent();      //为presenter添加view弱引用
    protected abstract int getLayout();       //为活动添加页面
    protected abstract void initView();      //实例化组件
    protected abstract void onPrepare();     //对组件进行初始化操作

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        presenter.deleteView();
        presenter=null;
    }

    public View getView(){
        return view;
    }
}