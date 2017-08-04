package com.luseen.logger;

import android.util.Log;

public class Logger {

    private Logger() {
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    private static LogType logType = LogType.INFO;
    private static boolean isLoggable = true;
    private static boolean isKotlin = false;
    private static String TAG = "Logger";

    private static void init(Builder builder) {
        Logger.logType = builder.getLogType();
        Logger.TAG = builder.getTag();
        Logger.isLoggable = builder.isIsLoggable();
        Logger.isKotlin = builder.isIsKotlin();
    }

    public static void e(Object message) {
        if (isLoggable) {
            Log.e(TAG, "| " + makeLog(message, "e"));
        }
    }

    public static void e(Object message, Throwable throwable) {
        if (isLoggable) {
            Log.e(TAG, "| " + makeLog(message, "e"), throwable);
        }
    }

    public static void i(Object message) {
        if (isLoggable) {
            Log.i(TAG, "| " + makeLog(message, "i"));
        }
    }

    public static void w(Object message) {
        if (isLoggable) {
            Log.w(TAG, "| " + makeLog(message, "w"));
        }
    }

    public static void d(Object message) {
        if (isLoggable)
            Log.d(TAG, "| " + makeLog(message, "d"));
    }

    public static void log(Object message) {
        if (isLoggable) {
            String body = "| " + makeLog(message, "log");
            switch (logType) {
                case INFO:
                    Log.i(TAG, body);
                    break;
                case DEBUG:
                    Log.d(TAG, body);
                    break;
                case ERROR:
                    Log.e(TAG, body);
                    break;
                case WARN:
                    Log.w(TAG, body);
                    break;
            }
        }
    }

    private static String makeLog(Object message, String calledMethodName) {
        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        int currentIndex = -1;
        for (int i = 0; i < stackTraceElement.length; i++) {
            if (stackTraceElement[i].getMethodName().compareTo(calledMethodName) == 0) {
                currentIndex = ++i;
                break;
            }
        }

        StackTraceElement traceElement = Thread.currentThread().getStackTrace()[currentIndex];
        String fullClassName = traceElement.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = traceElement.getMethodName();
        int lineNumber = traceElement.getLineNumber();
        String logMessage = message == null ? null : message.toString();
        String postFix = isKotlin ? ".kt:" : ".java:";
        return logMessage + " | (" + className + postFix + lineNumber + ")";
    }

    public static class Builder {
        private static LogType logType = LogType.INFO;
        private static boolean isLoggable = true;
        private static boolean isKotlin = false;
        private static String tag = "Logger";

        public Builder logType(LogType logType) {
            Builder.logType = logType;
            return this;
        }

        public Builder isLoggable(boolean isLoggable) {
            Builder.isLoggable = isLoggable;
            return this;
        }

        public Builder tag(String tag) {
            Builder.tag = tag;
            return this;
        }

        public Builder setIsKotlin(boolean isKotlin) {
            Builder.isKotlin = isKotlin;
            return this;
        }

        public boolean isIsKotlin() {
            return isKotlin;
        }

        public void build() {
            init(this);
        }

        LogType getLogType() {
            return logType;
        }

        boolean isIsLoggable() {
            return isLoggable;
        }

        String getTag() {
            return tag;
        }
    }
}
