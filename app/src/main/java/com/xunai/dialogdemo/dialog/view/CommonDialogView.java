package com.xunai.dialogdemo.dialog.view;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.xunai.dialogdemo.dialog.CommonDialog;
import com.xunai.dialogdemo.presenter.BasePresenter;
import com.xunai.dialogdemo.presenter.IBaseView;
import com.xunai.dialogdemo.utils.TUtil;


public class CommonDialogView<T extends BasePresenter,I> extends FrameLayout implements ICommonDialogView<I>, IBaseView {

    //业务处理
    protected T mPresenter;
    //接口
    protected I iListener;
    //公用dialog容器
    protected CommonDialog dialog;
    //loading
    private KProgressHUD mCommitLoading;

    public CommonDialogView(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(getLayoutId(),this);
    }

    public CommonDialogView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(getLayoutId(),this);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initRender(I iListener,CommonDialog dialog) {
        this.iListener = iListener;
        this.dialog = dialog;
        //初始化presenter
        mPresenter = TUtil.getT(this,0);
        if (mPresenter != null) {
            mPresenter.setIView(this);
        }
    }

  /////////////////////////////////////////////////////////////////////////////////
  //
  //                      IBaseView
  //
  /////////////////////////////////////////////////////////////////////////////////

    @Override
    public void showDialogLoading(String msg) {
        if (mCommitLoading == null) {
            mCommitLoading = KProgressHUD.create(getContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
            mCommitLoading.setCancellable(false);
            if (msg != null && msg.length() > 0) {
                mCommitLoading.setLabel(msg);
            }
            if (!mCommitLoading.isShowing()) {
                mCommitLoading.show();
            }
        } else if (!mCommitLoading.isShowing() && hasWindowFocus()) {
            mCommitLoading.show();
        }
    }

    @Override
    public void hideDialogLoading() {
        if (mCommitLoading != null) {
            try {
                if (mCommitLoading.isShowing() && getContext() != null) {
                    mCommitLoading.dismiss();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        mCommitLoading = null;
    }

    @Override
    public void showNetError(String msg, int icon) {

    }


    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }


}
