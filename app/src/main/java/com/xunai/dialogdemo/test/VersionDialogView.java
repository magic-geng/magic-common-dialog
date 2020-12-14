package com.xunai.dialogdemo.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xunai.dialogdemo.R;
import com.xunai.dialogdemo.dialog.CommonDialog;
import com.xunai.dialogdemo.dialog.view.CommonDialogView;
import com.xunai.dialogdemo.presenter.BasePresenter;

public class VersionDialogView extends CommonDialogView<BasePresenter,IVersionDialogListener> implements View.OnClickListener {

    private TextView versionView;
    private TextView contentView;

    private TextView updateView;
    private TextView cancelView;

    public VersionDialogView(@NonNull Context context) {
        super(context);
    }

    public VersionDialogView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dv_version_layout;
    }

    @Override
    public void initRender(IVersionDialogListener iListener, CommonDialog dialog) {
        super.initRender(iListener,dialog);
        versionView = findViewById(R.id.v_text_view);
        versionView.setText(iListener.version());
        contentView = findViewById(R.id.v_content_view);
        contentView.setText(iListener.content());

        updateView = findViewById(R.id.v_update_view);
        updateView.setOnClickListener(this);
        cancelView = findViewById(R.id.v_cancel_view);
        cancelView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.v_update_view) {
            dialog.dismiss();
            iListener.onClickUpdate();
        } else if (v.getId() == R.id.v_cancel_view) {
            dialog.dismiss();
            iListener.onClickCancel();
        }
    }
}
