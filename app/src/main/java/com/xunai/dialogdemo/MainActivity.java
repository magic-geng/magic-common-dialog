package com.xunai.dialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xunai.dialogdemo.dialog.CommonDialog;
import com.xunai.dialogdemo.dialog.CommonDialogAnimation;
import com.xunai.dialogdemo.test.IVersionDialogListener;
import com.xunai.dialogdemo.test.VersionDialogView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.button);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVersionDialog(MainActivity.this, new IVersionDialogListener() {
                    @Override
                    public String version() {
                        return "1.1.0版本";
                    }

                    @Override
                    public String content() {
                        return "1.修改了部分版本兼容的问题。\\n2.修改了部分bug。\\n3.更新了版本用户体验问题。";
                    }

                    @Override
                    public void onClickUpdate() {
                        Log.e("CommonDialog","更新");
                    }

                    @Override
                    public void onClickCancel() {
                        Log.e("CommonDialog","取消");
                    }
                });
            }
        });
    }


    /**
     * 版本更新弹窗
     * */
    public static void showVersionDialog(Context context, IVersionDialogListener iVersionDialogListener) {
        CommonDialog dialog = new CommonDialog.Builder<IVersionDialogListener>(context)
                .setCommonDialogListener(iVersionDialogListener)
                .setViewClass(VersionDialogView.class)
                .setCancelable(false)
                .create();
        dialog.show(CommonDialogAnimation.COMMON_DIALOG_ANIMATION_SCALE);
    }
}
