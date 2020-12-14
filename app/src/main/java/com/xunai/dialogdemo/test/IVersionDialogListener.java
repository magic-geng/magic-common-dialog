package com.xunai.dialogdemo.test;

public interface IVersionDialogListener {

    //获取数据
    String version();
    String content();

    //点击事件
    void onClickUpdate();
    void onClickCancel();

}
