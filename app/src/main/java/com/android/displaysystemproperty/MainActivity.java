package com.android.displaysystemproperty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.displaysystemproperty.tools.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView logView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logView = findViewById(R.id.logView);

    }

    protected String getSystemProperty(String propName) {
        String TAG = "GetProperty";
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            Log.e(TAG, "Unable to read sysprop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e(TAG, "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }

    public void appendToLogView(final String s) {
        Tools.appendTextView(logView, s);
    }


    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        String s = b.getText().toString() + ": ";
        switch (view.getId()) {
            case R.id.button1:
                appendToLogView(s + getSystemProperty("gsm.version.baseband"));
                break;
            case R.id.button2:
                appendToLogView(s + getSystemProperty("ril.official_cscver"));
                break;
            case R.id.button3:
                appendToLogView(s + getSystemProperty("ro.build.PDA"));
                break;
            default:
                break;
        }
    }


}
