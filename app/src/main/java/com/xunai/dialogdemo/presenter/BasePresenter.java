package com.xunai.dialogdemo.presenter;

public class BasePresenter<T extends IBaseView> {

    protected T iView;
    public void setIView(T v) {
        this.iView = v;
    }

    public void onDestroy() {

    }
}
