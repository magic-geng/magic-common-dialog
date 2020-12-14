package com.xunai.dialogdemo.dialog;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({CommonDialogAnimation.COMMON_DIALOG_ANIMATION_DEFAULT, CommonDialogAnimation.COMMON_DIALOG_ANIMATION_DOWN_UP, CommonDialogAnimation.COMMON_DIALOG_ANIMATION_SCALE})
@Retention(RetentionPolicy.SOURCE)
public @interface CommonDialogAnimation {
    int COMMON_DIALOG_ANIMATION_DEFAULT = 0; //默认无动画
    int COMMON_DIALOG_ANIMATION_DOWN_UP = 1; //从下到上
    int COMMON_DIALOG_ANIMATION_SCALE = 2; //从小到大

}
