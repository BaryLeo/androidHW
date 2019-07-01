package com.wyu.takeleave.form;

import com.wyu.takeleave.BaseActivityPresenter;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

public class FormPresenter extends BaseActivityPresenter<Form> implements IForm.Presenter{
    private IForm.View view;

    private IForm.Model model;

    public FormPresenter(IForm.View view){
        this.view=view;
        this.model = new FormModel();
    }


    @Override
    protected void deleteView() {
        //解除引用，GC回收
        view = null;
        model = null;
    }

    @Override
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserType("学生");
        userInfo.setName("朱元璋");
        userInfo.setId("3117002516");
        userInfo.setCollege("智能制造学部");
        userInfo.setMajor("通信工程");
        userInfo.setClassId("170815");
        return userInfo;
    }

    @Override
    public Boolean setTakeLeaveForm(TakeLeaveForm takeLeaveForm) {
        //当提交新表单是，从这里传入数据
        return true;
    }

    @Override
    public Boolean auditingForm(TakeLeaveForm takeLeaveForm) {
        //表单审核结果
        return true;
    }

    @Override
    public Boolean cancelApply(TakeLeaveForm takeLeaveForm) {
        //取消表单申请
        return true;
    }
}
