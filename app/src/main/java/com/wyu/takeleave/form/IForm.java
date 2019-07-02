package com.wyu.takeleave.form;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

public interface IForm {
    interface View{
        //提交或者审核成功时调用
        void handleApplySuccess(String msg);
        //提交或者审核失败时调用
        void handleApplyFail(String msg);
    }

    interface Presenter{
        void handlePutTakeLeaveForm(TakeLeaveForm takeLeaveForm);
        void auditingForm(TakeLeaveForm takeLeaveForm);
        void cancelApply(TakeLeaveForm takeLeaveForm);
    }

    interface Model{
        void putTakeLeaveForm(TakeLeaveForm takeLeaveForm, ValueCallBack<String> putTakeLeaveListener);
    }
}
