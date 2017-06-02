package com.tim.doordashlite.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.tim.doordashlite.model.Restaurant;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Home on 6/1/17.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[]{ Restaurant.class };

    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile("ormlite_config.txt", classes);
    }
}
