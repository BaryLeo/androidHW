package com.wyu.takeleave.form;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

public interface IForm {
    interface View{
        //提交或者审核成功时调用
        void handleApplySuccess();
        //提交或者审核失败时调用
        void handleApplyFail(String msg);
    }

    interface Presenter{
        void putTakeLeaveForm(TakeLeaveForm takeLeaveForm);
        void auditingForm(TakeLeaveForm takeLeaveForm);
        void cancelApply(TakeLeaveForm takeLeaveForm);
    }

    interface Model{

    }
}
