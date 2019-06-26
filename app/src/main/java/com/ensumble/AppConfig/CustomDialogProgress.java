package com.ensumble.AppConfig;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.ContentLoadingProgressBar;

import com.ensumble.R;

public class CustomDialogProgress
{
    // vars
    Dialog progress;
    LinearLayout layout;
    ContentLoadingProgressBar contentLoadingProgressBar;
    TextView tv_please_wait;

    private static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    } // function getScreenWidth


    public void init(Context context) {
        progress = new Dialog(context);
        progress.setContentView(R.layout.progress_loading_dialog);
        progress.setCancelable(false);
        layout = progress.findViewById(R.id.layoutDialog);
        contentLoadingProgressBar = progress.findViewById(R.id.progressLoading);
        tv_please_wait = progress.findViewById(R.id.tv_please_wait);
        contentLoadingProgressBar.setVisibility(View.VISIBLE);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.getWindow().setLayout((int) (getScreenWidth((Activity) context) * .9), ViewGroup.LayoutParams.WRAP_CONTENT);


    } // function of init

    public void show() {
        progress.show();
    } // function of show

    public void dismiss() {
        progress.dismiss();
    } // function of dismiss
}

