package com.xunai.dialogdemo.presenter;

public interface IBaseView {

    //提交的loading
    void showDialogLoading(String msg);
    //隐藏loading
    void hideDialogLoading();
    //提示错误
    void showNetError(String msg, int icon);

}
