package com.jsonliang.paydemo;

import android.app.Application;

/**
 * Created by Jsonliang on 2016/11/24.
 */

public class App extends Application{
    private static  App mInstance ;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this ;
    }

    public static App getInstance(){
        return mInstance ;
    }
}
