package com.wyu.takeleave;

public abstract class BaseActivityPresenter<T extends BaseActivity>{
    protected abstract void deleteView();     //解除view与presenter的连接
}
