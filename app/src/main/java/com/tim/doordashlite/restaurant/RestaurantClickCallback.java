package com.tim.doordashlite.restaurant;

import com.tim.doordashlite.model.Restaurant;

/**
 * Created by Home on 6/1/17.
 */

public class RestaurantClickCallback {

    private OnRestaurantClickListener onRestaurantClickListener;

    public RestaurantClickCallback(OnRestaurantClickListener onRestaurantClickListener) {
        this.onRestaurantClickListener = onRestaurantClickListener;
    }

    public void onRestaurantClick(Restaurant restaurant) {
        onRestaurantClickListener.onRestaurantClick(restaurant);
    }
}
