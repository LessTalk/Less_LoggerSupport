package org.less.logger;

import android.app.Application;

import less.org.library.DealExceptionHandler;

/**
 * ===============================================
 * DEVELOPER : RenYang <br/>
 * DATE : 2016/11/1 <br/>
 * DESCRIPTION :
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new DealExceptionHandler().attachToApplication("aaaaaaaaaaaaaaaaaaaaaaaaa");
    }
}
