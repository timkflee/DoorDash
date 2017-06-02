package com.tim.doordashlite.restaurant;

import com.tim.doordashlite.model.Restaurant;

/**
 * Created by Home on 6/1/17.
 */

public class RestaurantClickCallback {

    private RestaurantsPresenter presenter;

    public RestaurantClickCallback(RestaurantsPresenter presenter) {
        this.presenter = presenter;
    }

    public void onRestaurantClick(Restaurant restaurant) {
        presenter.onRestaurantClick(restaurant);
    }
}
