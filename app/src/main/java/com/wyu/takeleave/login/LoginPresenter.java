package com.wyu.takeleave.login;

import com.wyu.takeleave.BaseActivityPresenter;

public class LoginPresenter extends BaseActivityPresenter<Login> implements ILogin.Presenter{
    private ILogin.View view;

    private ILogin.Model model;

    public LoginPresenter(ILogin.View view){
        this.view=view;
        this.model = new LoginModel();
    }


    @Override
    protected void deleteView() {
        //解除引用，GC回收
        view = null;
        model = null;
    }
}
