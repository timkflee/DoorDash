package com.tim.doordashlite.favorite;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.tim.doordashlite.manager.FavoriteManager;
import com.tim.doordashlite.model.Restaurant;
import com.tim.doordashlite.restaurant.OnRestaurantClickListener;

import java.util.List;

import javax.inject.Inject;

import toothpick.Toothpick;

/**
 * Created by Home on 6/1/17.
 */

public class FavoritePresenter implements LoaderManager.LoaderCallbacks<List<Restaurant>>, OnRestaurantClickListener {

    private static final int FAVORITE_LOADER_ID = 1;

    @Inject FavoriteManager favoriteManager;

    private Application application;
    private FavoriteView view;
    private LoaderManager loaderManager;

    public FavoritePresenter(Application application, FavoriteView view, LoaderManager loaderManager) {
        this.application = application;
        this.view = view;
        this.loaderManager = loaderManager;
        Toothpick.inject(this, Toothpick.openScope(application));
        loaderManager.initLoader(FAVORITE_LOADER_ID, null, this);
    }

    public void loadFavorites() {
        view.enableSpinner(true);
        loaderManager.getLoader(FAVORITE_LOADER_ID).forceLoad();
    }

    @Override
    public Loader<List<Restaurant>> onCreateLoader(int id, Bundle args) {
        return new FavoriteLoader(application, favoriteManager);
    }

    @Override
    public void onLoadFinished(Loader<List<Restaurant>> loader, List<Restaurant> data) {
        view.enableSpinner(false);
        view.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Restaurant>> loader) {

    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {
        view.goToDetails(restaurant.id, restaurant.name);
    }
}
