# magic-common-dialog
一个公用的dialog弹窗，可以传入自定义View和自定义接口：

CommonDialog dialog = new CommonDialog.Builder<IVersionDialogListener>(context)
                .setCommonDialogListener(iVersionDialogListener)
                .setViewClass(VersionDialogView.class)
                .setCancelable(false)
                .create();
dialog.show(CommonDialogAnimation.COMMON_DIALOG_ANIMATION_SCALE);
