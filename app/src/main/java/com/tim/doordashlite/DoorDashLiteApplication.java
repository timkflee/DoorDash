package com.tim.doordashlite;

import android.app.Application;

import com.tim.doordashlite.module.ApplicationModule;
import com.tim.doordashlite.module.DatabaseModule;
import com.tim.doordashlite.module.NetworkModule;

import toothpick.Scope;
import toothpick.Toothpick;

/**
 * Created by Home on 5/31/17.
 */

public class DoorDashLiteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final Scope appScope = Toothpick.openScope(this);
        appScope.installModules(new ApplicationModule(this),
                                new NetworkModule(),
                                new DatabaseModule(this));
    }
}
