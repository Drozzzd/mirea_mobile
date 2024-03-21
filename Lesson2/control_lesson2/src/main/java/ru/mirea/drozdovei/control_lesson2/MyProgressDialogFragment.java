package ru.mirea.drozdovei.control_lesson2;

import android.app.ProgressDialog;
import android.content.Context;

public class MyProgressDialogFragment extends ProgressDialog {
    public MyProgressDialogFragment(Context context) {
        super(context);
    }

    public MyProgressDialogFragment(Context context, int theme) {
        super(context, theme);
    }
}