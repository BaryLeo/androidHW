package com.wyu.takeleave.form;

import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.R;

public class Form extends BaseActivity<FormPresenter> implements IForm.View{
    @Override
    protected FormPresenter initPresent() {
        return new FormPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.takeleave;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onPrepare() {

    }
}
