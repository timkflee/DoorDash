package com.tim.doordashlite.module;

import android.app.Application;

import toothpick.config.Module;

/**
 * Created by Home on 5/31/17.
 */

public class ApplicationModule extends Module {

    public ApplicationModule(Application application) {
        bind(Application.class).toInstance(application);
    }
}
