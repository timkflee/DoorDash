package com.tim.doordashlite.favorite;

import com.tim.doordashlite.model.Restaurant;

import java.util.List;

/**
 * Created by Home on 6/1/17.
 */

public interface FavoriteView {
    void setData(List<Restaurant> restaurants);
    void enableSpinner(boolean enable);
    void goToDetails(int restaurantId, String restaurantName);
}
