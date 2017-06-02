package com.tim.doordashlite.restaurant;

import com.tim.doordashlite.model.Restaurant;

import java.util.List;

/**
 * Created by Home on 5/31/17.
 */

public interface RestaurantsView {
    void enableSpinner(boolean enable);
    void setData(List<Restaurant> restaurants);
    void goToDetails(int restaurantId, String restaurantName);
}
