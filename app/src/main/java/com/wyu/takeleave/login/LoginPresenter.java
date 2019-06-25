package com.wyu.takeleave.login;

import com.wyu.takeleave.BaseActivityPresenter;
import com.wyu.takeleave.util.UserInfo;

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

    @Override
    public void HandleLogin(String id,String password) {
        //这里进行数据对比
        //假数据，测试用，需要删除
        model.login(id, password, new OnLoginListener() {
            @Override
            public void loginSuccess(UserInfo userInfo) {
                view.loginFailure(new String("账号密码正确"));
                view.toMain(userInfo);
            }

            @Override
            public void loginFailed() {
                view.loginFailure(new String("账号或者密码不正确"));
            }
        });
    }
}
