package com.tim.doordashlite.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Home on 6/1/17.
 */

public class FavoriteDao<T, ID> extends BaseDaoImpl<T, ID> {

    protected FavoriteDao(Class<T> dataClass) throws SQLException {
        super(dataClass);
    }

    protected FavoriteDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FavoriteDao(ConnectionSource connectionSource, DatabaseTableConfig<T> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
