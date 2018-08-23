package com.android.displaysystemproperty.tools;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class Tools {

    public static void appendTextView(final TextView textView, final String string) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String temp = string + "\n" + textView.getText().toString();
                textView.setText(temp);
            }
        });
    }
}
