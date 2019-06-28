package com.wyu.takeleave.form;

import com.wyu.takeleave.BaseActivityPresenter;

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
}
