package com.xunai.dialogdemo.dialog.view;

import com.xunai.dialogdemo.dialog.CommonDialog;

public interface ICommonDialogView<I> {

    int getLayoutId();

    void initRender(I iListener, CommonDialog dialog);

    void onDestroy();

}
