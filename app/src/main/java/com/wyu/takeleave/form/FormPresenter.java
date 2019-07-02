package com.wyu.takeleave.form;

import com.wyu.takeleave.BaseActivityPresenter;
import com.wyu.takeleave.ValueCallBack;
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
    public void handlePutTakeLeaveForm(TakeLeaveForm takeLeaveForm) {
        //当提交新表单是，从这里传入数据
        model.putTakeLeaveForm(takeLeaveForm, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.handleApplySuccess(s);
            }

            @Override
            public void onFail(String msg) {
                view.handleApplyFail(msg);
            }
        });
    }

    @Override
    public void handleAuditingForm(TakeLeaveForm takeLeaveForm) {
        //表单审核

    }

    @Override
    public void handleCancelApply(TakeLeaveForm takeLeaveForm) {
        //取消表单申请
    }
}
