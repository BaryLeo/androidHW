package com.wyu.takeleave.start;

import com.wyu.takeleave.BaseActivityPresenter;

public class StartPresenter extends BaseActivityPresenter<Start> implements IStart.Presenter{
    private IStart.View view;

    private IStart.Model model;

    public StartPresenter(IStart.View view){
        this.view=view;
        this.model = new StartModel();
    }

    @Override
    protected void deleteView() {
        //解除引用，GC回收
        view = null;
        model = null;
    }
}
