package com.tim.doordashlite.module;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.tim.doordashlite.database.DatabaseHelper;
import com.tim.doordashlite.database.FavoriteDao;

import java.sql.SQLException;

import toothpick.config.Module;

/**
 * Created by Home on 6/1/17.
 */

public class DatabaseModule extends Module {

    public DatabaseModule(Application application) {
        setupDatabase(application);
    }

    public void setupDatabase(Application application) {
        final DatabaseHelper helper = OpenHelperManager.getHelper(application, DatabaseHelper.class);
        bind(DatabaseHelper.class).toInstance(helper);
        try {
            bind(FavoriteDao.class).toInstance(helper.getFavoriteDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
