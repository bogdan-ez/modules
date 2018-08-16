package com.ebp.network.utils;

import com.ebp.network.model.LogLevel;

import okhttp3.logging.HttpLoggingInterceptor;

public class LogMapper {

    private LogMapper() {
    }

    public static HttpLoggingInterceptor.Level getLogLevel(LogLevel logLevel) {
        switch (logLevel) {
            case BODY:
                return HttpLoggingInterceptor.Level.BODY;
            case BASIC:
                return HttpLoggingInterceptor.Level.BASIC;
            case HEADERS:
                return HttpLoggingInterceptor.Level.HEADERS;
            case NONE:
                return HttpLoggingInterceptor.Level.NONE;
            default:
                return HttpLoggingInterceptor.Level.NONE;
        }
    }
}
