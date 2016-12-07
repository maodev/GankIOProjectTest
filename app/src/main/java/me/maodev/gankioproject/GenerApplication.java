package me.maodev.gankioproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by maoyu on 16/12/5.
 */

public class GenerApplication extends Application {
    private static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext  = this;
    }

    public static Context getInstance(){
        return mcontext;
    }

}
