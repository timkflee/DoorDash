package com.tim.doordashlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tim.doordashlite.R;
import com.tim.doordashlite.model.Restaurant;

import java.sql.SQLException;

/**
 * Created by Home on 6/1/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "doordash.db";
    private static final int DATABASE_VERSION = 1;

    private FavoriteDao favoriteDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Restaurant.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Restaurant.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FavoriteDao getFavoriteDao() throws SQLException {
        if (favoriteDao == null) {
            favoriteDao = getDao(Restaurant.class);
        }

        return favoriteDao;
    }

    @Override
    public void close() {
        favoriteDao = null;
        super.close();
    }
}
