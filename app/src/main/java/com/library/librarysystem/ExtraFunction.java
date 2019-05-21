package com.library.librarysystem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ExtraFunction {
    private Context context;
    private Activity activity;
    public int color = Color.RED, id;
    public String message;
    private boolean connected = false;
    private Snackbar snackbar;


    public ExtraFunction(Context mContext, Activity mActivity) {
        context = mContext;
        activity = mActivity;
    }

    public boolean checkInternet(String snackAct) {
        Log.e("apkflow", "checkInternet");

        if (snackAct.equals("activity_main"))
            id = R.id.activity_main;
        else
            id = R.id.activity_login;

        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        } catch (Exception e) {
            Log.e("apkflow", e.getMessage());
        }

        if (!connected) {
            message = "No Internet Connection";
            Log.e("apkflow",message);

            snackbar = Snackbar.make(activity.findViewById(id), message, 3500);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.setAction("DISMISS", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        } else {
            Log.e("apkflow","Connected to Internet");
        }

        return  connected;
    }

}


