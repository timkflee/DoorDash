package com.tim.doordashlite.manager;

import com.j256.ormlite.dao.Dao;
import com.tim.doordashlite.database.FavoriteDao;
import com.tim.doordashlite.model.Restaurant;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Home on 6/1/17.
 */
@Singleton
public class FavoriteManager {

    @Inject FavoriteDao favoriteDao;

    public List<Restaurant> getRestaurants() {
        try {
            return favoriteDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public boolean addRestaurant(Restaurant restaurant) {
        try {
            final Dao.CreateOrUpdateStatus status = favoriteDao.createOrUpdate(restaurant);
            return status.isCreated();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeRestaurant(Restaurant restaurant) {
        try {
            final int numOfDeleted = favoriteDao.deleteById(restaurant.id);
            return numOfDeleted == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean favoriteExists(int id) {
        try {
            return favoriteDao.idExists(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
