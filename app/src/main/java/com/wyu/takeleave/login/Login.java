package com.wyu.takeleave.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.IntentActivity;
import com.wyu.takeleave.R;
import com.wyu.takeleave.student.Student;
import com.wyu.takeleave.teacher.Teacher;
import com.wyu.takeleave.util.UserInfo;

public class Login extends BaseActivity<LoginPresenter> implements ILogin.View{
    private EditText id;
    private EditText password;
    private Button logon;

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
        //视图初始化
        id = (EditText)findViewById(R.id.idInput);
        password = (EditText)findViewById(R.id.passwordInput);
        logon = (Button)findViewById(R.id.login);
    }

    @Override
    protected void onPrepare() {
        //业务逻辑层
        //监听button
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //处理登录事务
                presenter.HandleLogin(id.getText().toString(),password.getText().toString());
            }
        });
    }


    @Override
    public void toMain(UserInfo userInfo) {
        if ("老师".equals(userInfo.getUserType())||"辅导员".equals(userInfo.getUserType())){
            Intent intent = new Intent(this,Teacher.class);
//            intent.putExtra("userId",userInfo.getId());
//            intent.putExtra("userName",userInfo.getName());
            intent.putExtra("userInfo",userInfo);
            startActivity(intent);
            //销毁本activity，并回收内存
            IntentActivity.finishActivity(this);
        }

        if ("学生".equals(userInfo.getUserType())||"班级管理员".equals(userInfo.getUserType())){
            Intent intent = new Intent(this,Student.class);
//            intent.putExtra("userId",userInfo.getId());
//            intent.putExtra("userName",userInfo.getName());
            intent.putExtra("userInfo",userInfo);
            startActivity(intent);
            //销毁本activity，并回收内存
            IntentActivity.finishActivity(this);
        }
    }

    @Override
    public void loginFailure(String msg) {
        //将错误信息弹出
        Toast.makeText(Login.this,msg,Toast.LENGTH_SHORT).show();
    }
}
