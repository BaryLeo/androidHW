package com.wyu.takeleave.form;

import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

public interface IForm {
    interface View{

    }

    interface Presenter{
        UserInfo getUserInfo();
        Boolean setTakeLeaveForm(TakeLeaveForm takeLeaveForm);
        Boolean auditingForm(TakeLeaveForm takeLeaveForm);
        Boolean cancelApply(TakeLeaveForm takeLeaveForm);
    }

    interface Model{

    }
}
