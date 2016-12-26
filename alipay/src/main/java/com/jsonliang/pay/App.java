package com.jsonliang.pay;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jsonliang on 2016/11/30.
 */

public class App extends Application {

    private static  App mInstance ;
    private static  Context mContext ;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this ;
        mContext = this ;
    }

    public static App getInstance(){
        return mInstance ;
    }

    public Context getContext(){
        return mContext ;
    }
}
