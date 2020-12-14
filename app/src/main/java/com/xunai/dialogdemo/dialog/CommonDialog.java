package com.xunai.dialogdemo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.xunai.dialogdemo.R;
import com.xunai.dialogdemo.dialog.view.CommonDialogView;
import com.xunai.dialogdemo.dialog.view.ICommonDialogView;
import com.xunai.dialogdemo.utils.ScreenUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommonDialog extends Dialog {

    private Activity activity;
    private @CommonDialogAnimation int animation = CommonDialogAnimation.COMMON_DIALOG_ANIMATION_DEFAULT;
    private WeakReference<ICommonDialogView>iCommonDialogView;

    public CommonDialog(Context context) {
        super(context);
    }

    public void setCommonDialogView(WeakReference<ICommonDialogView> iCommonDialogView) {
        this.iCommonDialogView = iCommonDialogView;
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
        activity = (Activity)context;
    }

    public void show(@CommonDialogAnimation int animation) {
        this.animation = animation;
        show();
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity= Gravity.CENTER;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= ScreenUtils.getScreenHeight() - ScreenUtils.getStatusBarHeight();
        switch (animation) {
            case CommonDialogAnimation.COMMON_DIALOG_ANIMATION_DEFAULT:
                break;
            case CommonDialogAnimation.COMMON_DIALOG_ANIMATION_DOWN_UP:
                getWindow().setWindowAnimations(R.style.dialog_up_down_style);
                break;
            case CommonDialogAnimation.COMMON_DIALOG_ANIMATION_SCALE:
                getWindow().setWindowAnimations(R.style.dialog_scale_style);
                break;
        }
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setDimAmount(0.8f);
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void dismiss() {
        if (activity!=null && activity.isFinishing()) {
            return;
        }
        if (iCommonDialogView != null && iCommonDialogView.get() != null) {
            iCommonDialogView.get().onDestroy();
        }
        super.dismiss();
    }

    public static class Builder <I> {

        //上下文
        private Context mContext;
        //根视图
        private View layout;
        //公用dialog容器
        private CommonDialog dialog;
        //主视图
        private FrameLayout rootView;
        //contentView对应外部容器协议
        private ICommonDialogView iContainerView;
        //contentView外部接口
        private I iCommonDialogListener;
        //contentView对应的类
        private Class<? extends CommonDialogView>tClass;
        //点击手机返回按键是否允许对话框消失
        private boolean cancelable = false;
        //点击对话框外部区域是否允许对话框消失
        private boolean canceledOnTouchOutside = false;
        //顶部偏移(默认-20)
        private int topOffset = -20;

        public Builder(Context context) {
            this.mContext = context;
            dialog = new CommonDialog(context, R.style.MyDialog);
            dialog.setCancelable(false);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.dialog_common, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        public Builder setViewClass(Class<? extends CommonDialogView> tClass) {
            this.tClass = tClass;
            return this;
        }

        public Builder setCommonDialogListener(I iCommonDialogListener) {
            this.iCommonDialogListener = iCommonDialogListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setTopOffset(int topOffset) {
            this.topOffset = topOffset;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public CommonDialog create() {

            rootView = layout.findViewById(R.id.dc_root_view);
            Constructor ct= null;
            try {
                ct = tClass.getDeclaredConstructor(new Class[]{Context.class});
                ct.setAccessible(true);
                iContainerView = (ICommonDialogView) ct.newInstance(new Object[]{mContext});
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.topMargin = ScreenUtils.dp2px(topOffset);
            ((View) iContainerView).setLayoutParams(layoutParams);
            iContainerView.initRender(iCommonDialogListener,dialog);
            rootView.addView((View) iContainerView);
            dialog.setContentView(layout);
            dialog.setCommonDialogView(new WeakReference<>(iContainerView));
            dialog.setCancelable(cancelable);
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            return  dialog;
        }

    }


}
