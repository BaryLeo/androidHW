package com.wyu.takeleave.login;

import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.R;

public class Login extends BaseActivity<LoginPresenter> implements ILogin.View{
    @Override
    protected LoginPresenter initPresent() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onPrepare() {

    }
}
