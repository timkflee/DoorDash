package com.tim.doordashlite.favorite;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.tim.doordashlite.manager.FavoriteManager;
import com.tim.doordashlite.model.Restaurant;

import java.util.List;

/**
 * Created by Home on 6/1/17.
 */

public class FavoriteLoader extends AsyncTaskLoader<List<Restaurant>> {

    private FavoriteManager favoriteManager;

    public FavoriteLoader(Context context, FavoriteManager favoriteManager) {
        super(context);
        this.favoriteManager = favoriteManager;

    }

    @Override
    public List<Restaurant> loadInBackground() {
        return favoriteManager.getRestaurants();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
