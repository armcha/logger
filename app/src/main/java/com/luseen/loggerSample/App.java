package com.luseen.loggerSample;

import android.app.Application;

import com.luseen.logger.LogType;
import com.luseen.logger.Logger;

/**
 * Created by Chatikyan on 04.07.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Logger.Builder()
                .isLoggable(BuildConfig.DEBUG)
                .logType(LogType.WARN)
                .tag("MyTag")
                .build();
    }
}
