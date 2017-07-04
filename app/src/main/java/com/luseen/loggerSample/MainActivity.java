package com.luseen.loggerSample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luseen.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.log("Log Message");
        Logger.i("Info Message");
        Logger.e("Error Message");
        Logger.w("Warn Message");
        Logger.d("Debug Message");
        Logger.e(null);
        Logger.e("Error Message with ThrowAble", new Throwable("Some Error"));
    }
}
