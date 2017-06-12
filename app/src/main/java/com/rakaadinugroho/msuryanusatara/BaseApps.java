package com.rakaadinugroho.msuryanusatara;

import android.app.Application;
import android.content.Context;

import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by Raka Adi Nugroho on 5/10/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class BaseApps extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        BaseApps.context    = getApplicationContext();
    }

    public static Context getAppContext(){
        return BaseApps.context;
    }
}
